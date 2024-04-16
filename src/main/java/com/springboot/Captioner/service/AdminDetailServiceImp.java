package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Admin;
import com.springboot.Captioner.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailServiceImp implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 在此处根据用户名从数据库中加载用户信息
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with email: " + email);
        }
        System.out.println("loadUserByUsername 方法成功被调用！");
        // 将数据库中的用户信息封装成一个 UserDetails 对象并返回
        return org.springframework.security.core.userdetails.User
                .withUsername(admin.getEmail())
                .password(admin.getPassword())
                .roles("USER")
                .build();

    }
}

