package com.sushil.user_service.controller;

import com.sushil.user_service.model.User;
import com.sushil.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        return userService.login(user.getEmail(),user.getPassword());
    }

    @PostMapping("delete")
    public String delete(@RequestBody User user) {
        userService.delete(user);
        return "user deleted successfully";
    }

    @PostMapping("update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
