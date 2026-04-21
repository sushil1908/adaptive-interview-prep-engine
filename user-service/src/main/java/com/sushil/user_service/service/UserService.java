package com.sushil.user_service.service;

import com.sushil.user_service.model.User;
import com.sushil.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User register(User user) {
        return userRepo.save(user);
    }
}
