package com.applifting.monitorservice.service.impl;

import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.repository.EndpointRepo;
import com.applifting.monitorservice.repository.ResultRepo;
import com.applifting.monitorservice.repository.UserRepo;
import com.applifting.monitorservice.service.UserService;
import com.applifting.monitorservice.service.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl extends Authorization implements UserService {


    public UserServiceImpl(UserRepo userRepo, ResultRepo resultRepo, EndpointRepo endpointRepo) {
        super(userRepo, resultRepo, endpointRepo);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserByName(String name) {
        return userRepo.findUserByUserName(name);
    }

    @Override
    public List<User> getAllUsers(String accessToken) {
        validateUser(accessToken);
        return userRepo.findAll();
    }


}
