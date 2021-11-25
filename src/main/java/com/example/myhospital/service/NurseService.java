package com.example.myhospital.service;

import com.example.myhospital.dto.DoctorRegistrationDto;
import com.example.myhospital.dto.NurseRegistrationDto;
import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Nurse;

import java.util.List;

public interface NurseService {
    List<Nurse> getAllNurses();
    Nurse save(NurseRegistrationDto registrationDto);
    Nurse getNurseById(long id);
    void deleteNurseById(long id);
    Nurse updateNurse(NurseRegistrationDto registrationDto);
}
