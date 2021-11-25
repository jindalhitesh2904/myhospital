package com.example.myhospital;

import com.example.myhospital.config.SecurityConfiguration;
import com.example.myhospital.model.Admin;
import com.example.myhospital.model.Role;
import com.example.myhospital.model.User;
import com.example.myhospital.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StarterClass implements CommandLineRunner {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    public void run(String...args) throws Exception {
//        adminRepository.checks();
//        Role role=new Role("ADMIN");
//        Admin admin=new Admin("Hitesh","Jindal","admin@gmail.com",securityConfiguration.passwordEncoder().encode("0000"),"+919999999999","M",20, Arrays.asList(role));
//        Admin admin1=adminRepository.save(admin);

//        Role role=new Role("ADMIN");
//        User user=new User("Hitesh","Jindal","admin@gmail.com",securityConfiguration.passwordEncoder().encode("0000"),"+919999999999","M",20, Arrays.asList(role));
//        Admin admin=new Admin(user);
//        Admin admin1=adminRepository.save(admin);
//        logger.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
        System.out.println("admin created");
    }
}
