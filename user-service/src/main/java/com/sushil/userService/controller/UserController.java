package com.sushil.userService.controller;

import com.sushil.userService.dto.LoginRequest;
import com.sushil.userService.dto.LoginResponse;
import com.sushil.userService.dto.UpdateUserRequest;
import com.sushil.userService.dto.UserResponse;
import com.sushil.userService.model.User;
import com.sushil.userService.repo.UserRepo;
import com.sushil.userService.service.UserService;
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
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(response);
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

    @GetMapping("get/me")
    public ResponseEntity<UserResponse> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @DeleteMapping("delete/me")
    public ResponseEntity<String> deleteCurrentUser(){
        return ResponseEntity.ok(userService.deleteCurrentUser());
    }

    @PatchMapping("update/me")
    public ResponseEntity<UserResponse> updateCurrentUser(@RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateCurrentUser(request));
    }
}
