package com.applifting.monitorservice.repository;

import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result, Integer> {

    @Query(value="SELECT * FROM appliftingdb.result LIMIT 10", nativeQuery = true)
    List<Result> findFirstTenResults();

    void deleteAllByEndpointId(Endpoint endpoint);

}
