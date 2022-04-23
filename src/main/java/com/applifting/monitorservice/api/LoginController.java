package com.applifting.monitorservice.api;


import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestHeader String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString());
        User user = userService.getUserByName(username);
        log.info("User {} found", username);
        user.setAccessToken(UUID.randomUUID().toString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
}
