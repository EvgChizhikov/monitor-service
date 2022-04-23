package com.applifting.monitorservice.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import static javax.persistence.GenerationType.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer userId;
    private String userName;
    private String email;
    private String accessToken;

    public User(String accessToken) {
        this.accessToken = accessToken;
    }
}