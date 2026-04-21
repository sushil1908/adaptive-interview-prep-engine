package com.sushil.user_service.service;

import com.sushil.user_service.model.User;
import com.sushil.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User register(User user) {
        return userRepo.save(user);
    }

    public String login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOpt.get();
        if(user.getPassword().equals(password)) {
            return "Login successful";
        }
        else{
            throw new RuntimeException("Invalid credentials");
        }
    }

    public void delete(User user) {
        if (!userRepo.existsById(user.getId())) {
            throw new RuntimeException("User not found");
        }
        userRepo.delete(user);
    }

    public User update(User user) {
        Optional<User> userOpt = userRepo.findById(user.getId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user1 = userOpt.get();
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        return userRepo.save(user1);
    }
}
