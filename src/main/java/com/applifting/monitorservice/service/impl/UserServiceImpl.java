package com.applifting.monitorservice.service.impl;

import com.applifting.monitorservice.data.model.User;
import com.applifting.monitorservice.repository.UserRepo;
import com.applifting.monitorservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserByName(String name) {
        return userRepo.findUserByUserName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
