package com.applifting.monitorservice.service.impl;

import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.Result;
import com.applifting.monitorservice.repository.EndpointRepo;
import com.applifting.monitorservice.repository.ResultRepo;
import com.applifting.monitorservice.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ResultServiceImpl implements ResultService {

    private final ResultRepo resultRepo;
    private final EndpointRepo endpointRepo;
    private final EndpointServiceImpl endpointService;

    @Override
    public Result saveMonitoringResult(String endpointName, Integer statusCode, String payload) {
        Endpoint endpoint = endpointRepo.findEndpointByName(endpointName);
        LocalDateTime dateOfCheck = LocalDateTime.now();
        Result result = new Result(statusCode, payload, dateOfCheck, endpoint);
        endpoint.setDateOfLastCheck(dateOfCheck);
        endpointService.updateEndpoint(endpoint);
        log.info("Endpoint {} returned {} status code and {} message", endpointName, statusCode, payload);
        return resultRepo.save(result);
    }

    @Override
    public List<Result> findAll() {
        return resultRepo.findFirstTenResults();
    }

}
