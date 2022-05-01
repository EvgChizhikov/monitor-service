package com.applifting.monitorservice.api;

import com.applifting.monitorservice.MonitorServiceApplication;
import com.applifting.monitorservice.data.form.EndpointToUser;
import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.Result;
import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.service.EndpointService;
import com.applifting.monitorservice.service.ResultService;
import com.applifting.monitorservice.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;

@ContextConfiguration(classes = MonitorServiceApplication.class)
@SpringBootTest
class ApiControllerTest {

    private User user;
    private Endpoint endpoint;
    private Result result;
    private URI uri;
    private LocalDateTime time;
    private final String UUID = "e05a2056-c6f8-11ec-9d64-0242ac120002";
    private final String endpointName = "google";

    @InjectMocks
    ApiController apiController;

    @Spy
    private UserService userService;

    @Spy
    private EndpointService endpointService;

    @Spy
    private ResultService resultService;

    @BeforeEach
    public void setUp() {
        user = new User(1, "Batman", "batman@example.com", UUID);
        time = LocalDateTime.now();
        endpoint = new Endpoint(endpointName, "https://www.google.com/", time, time, 10, user);
        result = new Result(200, "OK", time, endpoint);
        Mockito.when(userService.getAllUsers(UUID)).thenReturn(Collections.singletonList(user));
        Mockito.when(userService.saveUser(user)).thenReturn(user);
        Mockito.when(resultService.findAll(any(), any())).thenReturn(Collections.singletonList(result));
        Mockito.when(endpointService.getAllEndpoints(any())).thenReturn(Collections.singletonList(endpoint));
        Mockito.when(endpointService.updateEndpoint(any(), any(), any())).thenReturn(endpoint);
        Mockito.when(endpointService.saveEndpointToUser(any(), any(), any(), any())).thenReturn(endpoint);
        Mockito.when(endpointService.getByEndpointId(any())).thenReturn(endpoint);
        Mockito.when(endpointService.getEndpointByName(any())).thenReturn(endpoint);
    }

    @AfterEach
    public void after() {
        clearInvocations();
    }

    @Test
    void getUsers() {
        Assert.assertEquals(ResponseEntity.ok().body(Collections.singletonList(user)), apiController.getUsers(UUID));
        Mockito.verify(userService, Mockito.times(1)).getAllUsers(any());
    }

    @Test
    void createUser() {
        uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/createUser").toUriString());
        Assert.assertEquals(ResponseEntity.created(uri).body(user), apiController.createUser(user));
        Mockito.verify(userService, Mockito.times(1)).saveUser(any());
    }

    @Test
    void getResults() {
        Assert.assertEquals(ResponseEntity.ok().body(Collections.singletonList(result)), apiController.getResults(endpointName, UUID));
        Mockito.verify(resultService, Mockito.times(1)).findAll(any(), any());
    }

    @Test
    void getEndpoints() {
        Assert.assertEquals(ResponseEntity.ok().body(Collections.singletonList(endpoint)), apiController.getEndpoints(UUID));
        Mockito.verify(endpointService, Mockito.times(1)).getAllEndpoints(any());
    }

    @Test
    void updateEndpoint() {
        Assert.assertEquals(ResponseEntity.ok().body(endpoint), apiController.updateEndpoint(UUID, new EndpointToUser(), "google"));
        Mockito.verify(endpointService, Mockito.times(1)).updateEndpoint(any(), any(), any());
    }

    @Test
    void addEndpoint() {
        EndpointToUser endpointToUser = new EndpointToUser();
        endpointToUser.setEndpointName("google");
        endpointToUser.setMonitoredInterval(10);
        endpointToUser.setUrl("https://www.google.com/");
        uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/addEndpoint").toUriString());
        Assert.assertEquals(ResponseEntity.created(uri).body(endpoint), apiController.addEndpoint(UUID, endpointToUser));
        Mockito.verify(endpointService, Mockito.times(1)).saveEndpointToUser(any(), any(), any(), any());
        Mockito.verify(endpointService, Mockito.times(1)).getEndpointByName(any());
    }

    @Test
    void deleteEndpoint() {
        Assert.assertEquals(ResponseEntity.ok().build(), apiController.deleteEndpoint(new EndpointToUser(), UUID));
        Mockito.verify(endpointService, Mockito.times(1)).deleteEndpointByName(any(), any());
    }
}