package com.springboot.Captioner.service;

import com.springboot.Captioner.model.User;
import com.springboot.Captioner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        boolean userExists = false;
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            userExists = true;
        }
        return userExists;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
