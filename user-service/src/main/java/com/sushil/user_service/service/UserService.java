package com.sushil.user_service.service;

import com.sushil.user_service.dto.LoginRequest;
import com.sushil.user_service.dto.LoginResponse;
import com.sushil.user_service.dto.UpdateUserRequest;
import com.sushil.user_service.dto.UserResponse;
import com.sushil.user_service.model.User;
import com.sushil.user_service.repo.UserRepo;
import com.sushil.user_service.security.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public LoginResponse login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = userOpt.get();
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        String token= JwtUtil.generateToken(email);
        return new LoginResponse(token);
    }

    public void delete(Integer id) {
        if (!userRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        User user= userRepo.findById(id).get();
        if (!user.getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        userRepo.deleteById(id);
    }

    public User update(User user) {
        Optional<User> userOpt = userRepo.findById(user.getId());
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user1 = userOpt.get();

        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        if (!user1.getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        return userRepo.save(user1);
    }

    public UserResponse getUserbyId(Integer id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = userOpt.get();

        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        if (!user.getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        UserResponse userResponse=new UserResponse(user.getId(),user.getEmail(),user.getName());

        return userResponse;
    }

    public UserResponse getCurrentUser() {
        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        UserResponse response=new UserResponse(user.getId(),user.getEmail(),user.getName());
        return response;
    }

    public String deleteCurrentUser() {
        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepo.delete(user);
        return "User Deleted Successfully";
    }

    public UserResponse updateCurrentUser(UpdateUserRequest request) {
        String email= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        User user= userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        User saveduser=userRepo.save(user);
        return new UserResponse(saveduser.getId(), saveduser.getEmail(), saveduser.getName());
    }
}
