package com.applifting.monitorservice.repository;

import com.applifting.monitorservice.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserByUserName(String name);
    User findUserByAccessToken(String accessToken);
    List<User> findAll();
}
