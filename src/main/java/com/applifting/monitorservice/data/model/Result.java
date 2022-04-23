package com.applifting.monitorservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer resultId;
    private LocalDateTime dateOfCheck;
    private Integer statusCode;
    private String payload;
    @ManyToOne(targetEntity = Endpoint.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "endpoint_id")
    @JsonIgnore
    private Endpoint endpointId;

    public Result(Integer statusCode, String payload, LocalDateTime dateOfCheck, Endpoint endpointId) {
        this.statusCode = statusCode;
        this.payload = payload;
        this.endpointId = endpointId;
        this.dateOfCheck = dateOfCheck;
    }
}
