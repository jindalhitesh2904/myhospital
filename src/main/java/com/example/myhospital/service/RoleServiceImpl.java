package com.example.myhospital.service;

import com.example.myhospital.model.Role;
import com.example.myhospital.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}
