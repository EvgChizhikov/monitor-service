package com.applifting.monitorservice.service;

import com.applifting.monitorservice.data.model.Endpoint;


public interface EndpointService {

    Endpoint saveEndpointToUser(String accessToken, String endpointName, String url, Integer monitoredInterval);

    Endpoint updateEndpoint(Endpoint endpoint);

    Endpoint getAllEndpoints(String accessToken);

    Endpoint getByEndpointId(String accessToken, Integer id);

    void deleteEndpointByName(String accessToken, String name);

}
