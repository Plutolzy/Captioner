package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.PlayDTOBean;
import com.springboot.Captioner.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class DisplayController {

    @PostMapping("/display")
    public ResponseEntity<UserResponse> registerUser(@RequestBody PlayDTOBean playDTOBean) {
        // 播放成功
        UserResponse response = new UserResponse();
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}
