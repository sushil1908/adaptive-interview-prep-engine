package com.sushil.user_service.service;

import com.sushil.user_service.dto.UserResponse;
import com.sushil.user_service.model.User;
import com.sushil.user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1=userRepo.save(user);

        UserResponse userResponse=new UserResponse();
        userResponse.setEmail(user1.getEmail());
        userResponse.setId(user1.getId());
        userResponse.setName(user1.getName());
        return userResponse;
    }

    public String login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = userOpt.get();
        if(passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful";
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    public void delete(Integer id) {
        if (!userRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepo.deleteById(id);
    }

    public User update(User user) {
        Optional<User> userOpt = userRepo.findById(user.getId());
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user1 = userOpt.get();
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        return userRepo.save(user1);
    }

    public UserResponse getUserbyId(Integer id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = userOpt.get();
        UserResponse userResponse=new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        return userResponse;
    }
}
