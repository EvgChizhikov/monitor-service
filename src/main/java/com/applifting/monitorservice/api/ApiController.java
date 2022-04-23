package com.applifting.monitorservice.api;

import com.applifting.monitorservice.data.form.EndpointToUser;
import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.Result;
import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.logic.Sender;
import com.applifting.monitorservice.service.EndpointService;
import com.applifting.monitorservice.service.ResultService;
import com.applifting.monitorservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;
    private final EndpointService endpointService;
    private final ResultService resultService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers(@RequestHeader(value = "accessToken") String accessToken) {
        return ResponseEntity.ok().body(userService.getAllUsers(accessToken));
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/createUser").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @GetMapping(value = "/{endpointName}/results", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Result>> getResults(@PathVariable String endpointName,
                                                   @RequestHeader(value = "accessToken")  String token) {
        return ResponseEntity.ok().body(resultService.findAll(token, endpointName));
    }

    @PostMapping("/addEndpoint")
    public ResponseEntity<?> addEndpoint(@RequestHeader(value = "accessToken") String accessToken, @RequestBody EndpointToUser form) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/addEndpoint").toUriString());
        Endpoint endpoint = endpointService.saveEndpointToUser(
                accessToken, form.getEndpointName(), form.getUrl(), form.getMonitoredInterval());
        Sender sender = new Sender(resultService, form.getEndpointName(), form.getMonitoredInterval(), form.getUrl());
        sender.start();
        return ResponseEntity.created(uri).body(endpoint);
    }

    @PostMapping("/deleteEndpoint")
    public ResponseEntity<?> deleteEndpoint(@RequestBody EndpointToUser form, @RequestHeader(value = "accessToken") String accessToken) {
        endpointService.deleteEndpointByName(accessToken, form.getEndpointName());
        return ResponseEntity.ok().build();
    }

}

