package com.example.myhospital.repository;

import com.example.myhospital.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
//    Admin findByEmail(String username);
}
