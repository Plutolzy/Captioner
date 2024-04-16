package com.springboot.Captioner.service;

import com.springboot.Captioner.model.User;
import com.springboot.Captioner.model.UserDTO;
import com.springboot.Captioner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        // 添加日志记录
        System.out.println("Converting play to DTO: " + dto);
        return dto;
    }

    @Override
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // 这里假设principal直接就是email
        }
        return null; // 或者抛出异常
    }
}
