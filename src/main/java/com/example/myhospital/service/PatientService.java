package com.example.myhospital.service;

import com.example.myhospital.dto.PatientRegistrationDto;
import com.example.myhospital.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient save(PatientRegistrationDto registrationDto);
    Patient getPatientById(long id);
    void deletePatientById(long id);
    Patient updatePatient(PatientRegistrationDto registrationDto);
    Patient getPatientByUser_Id(long id);
    Patient getPatientByEmail(String email);
}
