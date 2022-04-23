package com.applifting.monitorservice.repository;

import com.applifting.monitorservice.data.model.Endpoint;
;
import com.applifting.monitorservice.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointRepo extends JpaRepository<Endpoint, Integer> {

    Endpoint findAllByOwner(User owner);
    Endpoint findByNameAndOwner(String endpointName, User owner);
    Endpoint findByName(String endpointName);
    Endpoint findByEndpointIdAndOwner(Integer id, User owner);
    void deleteByName(String name);
}
