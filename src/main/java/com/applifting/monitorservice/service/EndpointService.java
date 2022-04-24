package com.applifting.monitorservice.service;

import com.applifting.monitorservice.data.form.EndpointToUser;
import com.applifting.monitorservice.data.model.Endpoint;

import java.util.List;


public interface EndpointService {

    Endpoint saveEndpointToUser(String accessToken, String endpointName, String url, Integer monitoredInterval);

    Endpoint updateEndpointInThread(Endpoint endpoint);

    Endpoint updateEndpoint(String accesstoken, EndpointToUser endpoint, String endpointName);

    Endpoint getEndpointByName(String endpointName);

    List<Endpoint> getAllEndpoints(String accessToken);

    Endpoint getByEndpointId(Integer id);

    void deleteEndpointByName(String accessToken, String name);

}
