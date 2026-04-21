package com.sushil.user_service.controller;

import com.sushil.user_service.dto.LoginRequest;
import com.sushil.user_service.dto.UserResponse;
import com.sushil.user_service.model.User;
import com.sushil.user_service.repo.UserRepo;
import com.sushil.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@RequestBody User user) {
        UserResponse userResponse= userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String result= userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User user1= userService.update(user);
        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
         UserResponse response=userService.getUserbyId(id);
         return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
