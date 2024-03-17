package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.User;
import com.springboot.Captioner.model.UserResponse;
import com.springboot.Captioner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponse> loginUser(@RequestBody User user) {
        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // 登录成功
            UserResponse response = new UserResponse();
            response.setSuccess(true);
            response.setMessage("Login successful");
            System.out.println("niubi");
            response.setEmail(existingUser.getEmail());
            return ResponseEntity.ok(response);
        } else {
            // 登录失败
            UserResponse response = new UserResponse();
            response.setSuccess(false);
            response.setMessage("Invalid credentials");
            System.out.println("buniubi" );
            // Email为空，因为登录失败不应返回邮箱信息
            response.setEmail(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

}
