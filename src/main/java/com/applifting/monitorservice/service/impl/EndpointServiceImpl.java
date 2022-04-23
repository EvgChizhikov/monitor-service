package com.applifting.monitorservice.service.impl;

import com.applifting.monitorservice.data.model.Endpoint;

import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.repository.EndpointRepo;
import com.applifting.monitorservice.repository.ResultRepo;
import com.applifting.monitorservice.repository.UserRepo;
import com.applifting.monitorservice.service.EndpointService;
import com.applifting.monitorservice.service.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class EndpointServiceImpl extends Authorization implements EndpointService {


    public EndpointServiceImpl(UserRepo userRepo, ResultRepo resultRepo, EndpointRepo endpointRepo) {
        super(userRepo, resultRepo, endpointRepo);
    }

    @Override
    public Endpoint saveEndpointToUser(String accessToken, String endpointName, String url, Integer monitoredInterval) {
        User user = validateUser(accessToken);
        Endpoint endpoint = new Endpoint(endpointName, url, LocalDateTime.now(), LocalDateTime.now(), monitoredInterval, user);
        return endpointRepo.save(endpoint);
    }

    @Override
    public Endpoint updateEndpoint(Endpoint endpoint) {
        return endpointRepo.save(endpoint);
    }

    @Override
    public Endpoint getAllEndpoints(String accessToken) {
        User user = validateUser(accessToken);
        return endpointRepo.findAllByOwner(user);
    }

    @Override
    public Endpoint getByEndpointId(String accessToken, Integer id) {
        User user = validateUser(accessToken);
        return endpointRepo.findByEndpointIdAndOwner(id, user);
    }

    @Override
    public void deleteEndpointByName(String accessToken, String name) {
        User user = validateUser(accessToken);
        Endpoint endpoint = endpointRepo.findByNameAndOwner(name, user);
        if (endpoint == null) {
            log.error("Endpoint {} was not found", name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endpoint was not found");
        }
        resultRepo.deleteAllByEndpointId(endpoint);
        endpointRepo.deleteByName(name);
    }
}
