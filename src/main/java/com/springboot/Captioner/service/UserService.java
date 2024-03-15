package com.springboot.Captioner.service;

import com.springboot.Captioner.model.User;

public interface UserService {
    void saveUser(User user);

    boolean isUserPresent(User user);

    User getUserByEmail(String email);
}
