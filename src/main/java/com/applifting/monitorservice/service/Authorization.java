package com.applifting.monitorservice.service;

import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.repository.EndpointRepo;
import com.applifting.monitorservice.repository.ResultRepo;
import com.applifting.monitorservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Slf4j
public abstract class Authorization {

    protected final UserRepo userRepo;
    protected final ResultRepo resultRepo;
    protected final EndpointRepo endpointRepo;

    public User validateUser(final String token) {
        User user = userRepo.findUserByAccessToken(token);
        if (user == null) {
            log.error("User was not found");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User was not found");
        } else
            return user;
    }
}
