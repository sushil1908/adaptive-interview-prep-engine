package com.sushil.user_service.controller;

import com.sushil.user_service.dto.LoginRequest;
import com.sushil.user_service.dto.UserResponse;
import com.sushil.user_service.model.User;
import com.sushil.user_service.repo.UserRepo;
import com.sushil.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public UserResponse register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "user deleted successfully";
    }

    @PutMapping("update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("get/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserbyId(id);
    }
}
