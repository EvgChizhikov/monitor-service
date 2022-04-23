package com.applifting.monitorservice.service.impl;

import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.Result;

import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.repository.EndpointRepo;
import com.applifting.monitorservice.repository.ResultRepo;
import com.applifting.monitorservice.repository.UserRepo;
import com.applifting.monitorservice.service.EndpointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class EndpointServiceImpl implements EndpointService {

    private final EndpointRepo endpointRepo;
    private final UserRepo userRepo;
    private final ResultRepo resultRepo;

    @Override
    public Endpoint saveEndpointToUserByName(String username, String endpointName, String url, Integer monitoredInterval) {
        User user = userRepo.findUserByUserName(username); //MAKE A CHECK!!
        Endpoint endpoint = new Endpoint(endpointName, url, LocalDateTime.now(), LocalDateTime.now(), monitoredInterval, user);
        return endpointRepo.save(endpoint);
    }

    @Override
    public Endpoint updateEndpoint(Endpoint endpoint) {
        return endpointRepo.save(endpoint);
    }

    @Override
    public Endpoint getEndpointByName(String name) {
        return endpointRepo.findEndpointByName(name);
    }

    @Override
    public void deleteEndpointByName(String name) {
        Endpoint endpoint = endpointRepo.findEndpointByName(name);
        resultRepo.deleteAllByEndpointId(endpoint);
        endpointRepo.deleteByName(name);
    }
}
