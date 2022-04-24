package com.applifting.monitorservice.repository;

import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EndpointRepo extends JpaRepository<Endpoint, Integer> {

    List<Endpoint> findAllByOwner(User owner);
    Endpoint findByNameAndOwner(String endpointName, User owner);
    Endpoint findByName(String endpointName);
    Endpoint findByEndpointId(Integer id);
    void deleteByName(String name);
}
