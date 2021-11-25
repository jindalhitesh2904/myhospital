package com.example.myhospital.service;

import com.example.myhospital.dto.DoctorRegistrationDto;
import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Role;
import com.example.myhospital.model.User;
import com.example.myhospital.repository.DoctorRepository;
import com.example.myhospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Doctor save(DoctorRegistrationDto doctorRegistrationDto) {
//        Role role=roleService.getRoleByName("DOCTOR");
//        Doctor doctor=new Doctor(doctorRegistrationDto.getFirst_name(), doctorRegistrationDto.getLast_name(),doctorRegistrationDto.getEmail(), passwordEncoder.encode(doctorRegistrationDto.getPassword()), doctorRegistrationDto.getMobile_no(), doctorRegistrationDto.getGender(), doctorRegistrationDto.getAge(),Arrays.asList(role),doctorRegistrationDto.getSalary(),doctorRegistrationDto.getDepartment(),doctorRegistrationDto.getDesignation());
//        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getAuthorities());
//        return doctorRepository.save(doctor);
//    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(long id){
        return doctorRepository.getDoctorById(id);
    }

    @Override
    public void deleteDoctorById(long id) {
        doctorRepository.deleteById(id);
    }



    public Doctor updateDoctor(DoctorRegistrationDto registrationDto){
        Doctor doctor=doctorRepository.getDoctorById(registrationDto.getId());
        User user= doctor.getUser();
        user.setMobile_no(registrationDto.getMobile_no());
//        user.setFirst_name(registrationDto.getFirst_name());
//        user.setLast_name(registrationDto.getLast_name());
        user.setAge(registrationDto.getAge());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        doctor.setUser(user);
        doctor.setSalary(registrationDto.getSalary());
        doctor.setDesignation(registrationDto.getDesignation());
        doctor.setDepartment(registrationDto.getDepartment());
        doctor.setQualification(registrationDto.getQualification());
        userRepository.save(user);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorByUser_Id(long id) {
        return doctorRepository.getDoctorByUser_Id(id);
    }

    @Override
    public List<Doctor> getDoctorsByDepartment(String department) {
        return doctorRepository.findAllByDepartment(department);
    }

    //    }
    @Override
    public Doctor save(DoctorRegistrationDto doctorRegistrationDto){
//        Optional<Role> role=roleService.getRoleByName("DOCTOR");
        User user=new User(doctorRegistrationDto.getFirst_name(),doctorRegistrationDto.getLast_name(),doctorRegistrationDto.getEmail(),passwordEncoder.encode(doctorRegistrationDto.getPassword()),doctorRegistrationDto.getMobile_no(), doctorRegistrationDto.getGender(), doctorRegistrationDto.getAge(), Arrays.asList(new Role("DOCTOR")));
        Doctor doctor=new Doctor(user,doctorRegistrationDto.getSalary(), doctorRegistrationDto.getDesignation(), doctorRegistrationDto.getDepartment(),doctorRegistrationDto.getQualification());
        userRepository.save(user);
        return doctorRepository.save(doctor);
    }

}
