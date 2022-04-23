package com.applifting.monitorservice.service;

import com.applifting.monitorservice.data.model.Result;

import java.util.List;

public interface ResultService {

    Result saveMonitoringResult(String endpointName, Integer statusCode, String payload);

    List<Result> findAll(String accessToken, String endpointName);

}
