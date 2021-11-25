package com.example.myhospital.service;

import com.example.myhospital.dto.NurseRegistrationDto;
import com.example.myhospital.model.Nurse;
import com.example.myhospital.model.Role;
import com.example.myhospital.model.User;
import com.example.myhospital.repository.NurseRepository;
import com.example.myhospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class NurseServiceImpl implements NurseService{
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Nurse save(NurseRegistrationDto nurseRegistrationDto) {
//        Role role=roleService.getRoleByName("DOCTOR");
//        Nurse nurse=new Nurse(nurseRegistrationDto.getFirst_name(), nurseRegistrationDto.getLast_name(),nurseRegistrationDto.getEmail(), passwordEncoder.encode(nurseRegistrationDto.getPassword()), nurseRegistrationDto.getMobile_no(), nurseRegistrationDto.getGender(), nurseRegistrationDto.getAge(),Arrays.asList(role),nurseRegistrationDto.getSalary(),nurseRegistrationDto.getDepartment(),nurseRegistrationDto.getDesignation());
//        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getAuthorities());
//        return nurseRepository.save(nurse);
//    }

    @Override
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    @Override
    public Nurse getNurseById(long id){
        return nurseRepository.getNurseById(id);
    }

    @Override
    @Transactional
    public void deleteNurseById(long id) {
        nurseRepository.deleteById(id);
    }


    @Transactional
    public Nurse updateNurse(NurseRegistrationDto registrationDto){
        Nurse nurse=nurseRepository.getNurseById(registrationDto.getId());
        User user= nurse.getUser();
        user.setMobile_no(registrationDto.getMobile_no());
//        user.setFirst_name(registrationDto.getFirst_name());
//        user.setLast_name(registrationDto.getLast_name());
        user.setAge(registrationDto.getAge());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        nurse.setUser(user);
        nurse.setSalary(registrationDto.getSalary());
        userRepository.save(user);
        return nurseRepository.save(nurse);
    }

    //    }
    @Override
    @Transactional
    public Nurse save(NurseRegistrationDto nurseRegistrationDto){
//        Optional<Role> role=roleService.getRoleByName("DOCTOR");
        User user=new User(nurseRegistrationDto.getFirst_name(),nurseRegistrationDto.getLast_name(),nurseRegistrationDto.getEmail(),passwordEncoder.encode(nurseRegistrationDto.getPassword()),nurseRegistrationDto.getMobile_no(), nurseRegistrationDto.getGender(), nurseRegistrationDto.getAge(), Arrays.asList(new Role("NURSE")));
        Nurse nurse=new Nurse(user,nurseRegistrationDto.getSalary());
        userRepository.save(user);
        return nurseRepository.save(nurse);
    }
}
