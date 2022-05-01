package com.applifting.monitorservice.api;

import com.applifting.monitorservice.MonitorServiceApplication;
import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.service.UserService;
import org.junit.Assert;
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
import static org.mockito.ArgumentMatchers.any;

@ContextConfiguration(classes = MonitorServiceApplication.class)
@SpringBootTest
class LoginControllerTest {

    private final String NAME = "Batman";

    @InjectMocks
    LoginController loginController;

    @Spy
    private UserService userService;

    @BeforeEach
    public void setUp() {
        Mockito.when(userService.getUserByName(any())).thenReturn(new User(1, "Batman", "batman@example.com", "e05a2056-c6f8-11ec-9d64-0242ac120002"));
    }

    @Test
    void login() {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString());
        Assert.assertEquals(ResponseEntity.created(uri).body(userService.saveUser(new User(1, "Batman", "batman@example.com", "e05a2056-c6f8-11ec-9d64-0242ac120002"))), loginController.login(NAME));
        Mockito.verify(userService, Mockito.times(1)).getUserByName(any());
    }

}