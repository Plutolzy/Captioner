package com.springboot.Captioner.service;

import com.springboot.Captioner.model.User;
import com.springboot.Captioner.model.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    boolean isUserPresent(User user);

    User getUserByEmail(String email);

    public List<UserDTO> getAllUsers();

    String getCurrentUserEmail();
}
