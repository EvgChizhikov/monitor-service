package com.applifting.monitorservice.repository;

import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.data.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result, Integer> {

//    @Query(value="SELECT * FROM appliftingdb.result LIMIT 10", nativeQuery = true)
    List<Result> findTop10ByEndpointIdOrderByDateOfCheck(Endpoint endpoint);
//    List<Result> findFirstTenResults();

    void deleteAllByEndpointId(Endpoint endpoint);

}
