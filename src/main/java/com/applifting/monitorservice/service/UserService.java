package com.applifting.monitorservice.service;

import com.applifting.monitorservice.data.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User getUserByName(String name);

    List<User> getAllUsers(String accessToken);
}
