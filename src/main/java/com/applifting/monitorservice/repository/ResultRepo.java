package com.applifting.monitorservice.repository;

import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result, Integer> {

    List<Result> findTop10ByEndpointIdOrderByDateOfCheckDesc(Endpoint endpoint);

    void deleteAllByEndpointId(Endpoint endpoint);

}
