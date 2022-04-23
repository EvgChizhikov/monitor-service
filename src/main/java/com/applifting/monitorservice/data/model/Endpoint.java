package com.applifting.monitorservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endpoint  {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer endpointId;
    private String name;
    private String url;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastCheck;
    private Integer monitoredInterval;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    @JsonIgnore
    private User owner;

    public Endpoint(String name, String url, LocalDateTime dateOfCreation, LocalDateTime dateOfLastCheck, Integer monitoredInterval, User owner) {
        this.name = name;
        this.url = url;
//        this.dateOfCreation = dateOfCreation;
        this.dateOfLastCheck = dateOfLastCheck;
        this.monitoredInterval = monitoredInterval;
        this.owner = owner;
    }
}
