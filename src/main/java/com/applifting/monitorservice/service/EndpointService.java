package com.applifting.monitorservice.service;

import com.applifting.monitorservice.data.model.Endpoint;

import java.time.LocalDateTime;

public interface EndpointService {

    Endpoint saveEndpointToUserByName(String userName, String endpointName, String url, Integer monitoredInterval);

    Endpoint updateEndpoint(Endpoint endpoint);

    Endpoint getEndpointByName(String name);

    void deleteEndpointByName(String name);

}
