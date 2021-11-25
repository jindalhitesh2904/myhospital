package com.example.myhospital.service;

import com.example.myhospital.dto.DoctorRegistrationDto;
import com.example.myhospital.model.Doctor;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.print.Doc;
import java.util.List;

public interface DoctorService{
//    Doctor save(DoctorRegistrationDto doctorRegistrationDto);
    List<Doctor> getAllDoctors();
    Doctor save(DoctorRegistrationDto registrationDto);
    Doctor getDoctorById(long id);
    void deleteDoctorById(long id);
    Doctor updateDoctor(DoctorRegistrationDto registrationDto);
    Doctor getDoctorByUser_Id(long id);
    List<Doctor> getDoctorsByDepartment(String department);
}
