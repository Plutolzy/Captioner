package com.springboot.Captioner.service;

import com.springboot.Captioner.model.User;

public interface UserService {
    public void saveUser(User user);
    public boolean isUserPresent(User user);
}
