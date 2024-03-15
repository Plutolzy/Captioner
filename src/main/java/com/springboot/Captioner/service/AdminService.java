package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Admin;

public interface AdminService {
    public void saveAdmin(Admin admin);

    public boolean isAdminPresent(Admin admin);
}
