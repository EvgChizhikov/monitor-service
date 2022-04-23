package com.applifting.monitorservice.repository;

import com.applifting.monitorservice.data.model.Endpoint;
;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointRepo extends JpaRepository<Endpoint, Integer> {

    Endpoint findEndpointByName(String name);
    void deleteByName(String name);
}
