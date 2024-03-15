package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.User;
import com.springboot.Captioner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    // 注册新用户
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // By finding Email
        if (userService.isUserPresent(user)) {
            return "User already exists";
        } else {
            userService.saveUser(user);
            return "User registered successfully";
        }
    }

    // 用户登录
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        // 根据用户邮箱从数据库中获取用户信息
        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }
}
