package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Admin;
import com.springboot.Captioner.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {
//    @Autowired
//    PlayRepository playRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void saveAdmin(Admin admin) {
        admin.setPassword(admin.getPassword());
        adminRepository.save(admin);
    }

    @Override
    public boolean isAdminPresent(Admin admin) {
        boolean adminExists = false;
        Admin existingAdmin = adminRepository.findByEmail(admin.getEmail());
        if (existingAdmin != null) {
            adminExists = true;
        }
        return adminExists;
    }
}
