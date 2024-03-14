package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findByEmail(String email);
}
