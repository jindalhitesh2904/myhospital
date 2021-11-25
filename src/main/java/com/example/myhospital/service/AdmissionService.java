package com.example.myhospital.service;

import com.example.myhospital.model.Admission;
import com.example.myhospital.model.Doctor;

import java.util.List;

public interface AdmissionService {
    List<Admission> getAdmissionsByDoctor_IdAndStatus(long id, String status);
    List<Admission> getAdmissionsByPatient_Id(long id);
    List<Admission> getAdmissionsByDoctor_Id(long id);
    List<Admission> getAdmissionsByStatus(String status);
    Admission getAdmissionById(long id);
    Admission save(long patient_id,long doctor_id,long room_id);
    void deleteAdmissionById(long id);
    void saveAdmission(Admission admission);
}
