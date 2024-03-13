package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Plays;
import com.springboot.Captioner.model.User;
import com.springboot.Captioner.repository.PlayRepository;
import com.springboot.Captioner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImp implements UserService {
//    @Autowired
//    PlayRepository playRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(user.getPassword());
//        user.setStatus("VERIFIED");
//        Plays userPlay = playRepository.findByPlay("SITE_USER");
//        user.setPlays(new HashSet<Plays>(Arrays.asList(userPlay)));
        userRepository.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        boolean userExists = false;
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser!=null){
            userExists = true;
        }
        return userExists;
    }
}
