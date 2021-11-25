package com.example.myhospital.service;

import com.example.myhospital.model.*;
import com.example.myhospital.repository.AdmissionRepository;
import com.example.myhospital.repository.DoctorRepository;
import com.example.myhospital.repository.PatientRepository;
import com.example.myhospital.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class AdmissionServiceImpl implements AdmissionService{
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Admission> getAdmissionsByDoctor_IdAndStatus(long id, String status) {
        return admissionRepository.findAllByDoctor_IdAndStatus(id,status);
    }

    @Override
    public List<Admission> getAdmissionsByPatient_Id(long id) {
        return admissionRepository.findAllByPatient_Id(id);
    }

    @Override
    public List<Admission> getAdmissionsByDoctor_Id(long id) {
        return admissionRepository.findAllByDoctor_Id(id);
    }

    @Override
    public List<Admission> getAdmissionsByStatus(String status) {
        return admissionRepository.findAllByStatus(status);
    }

    @Override
    public Admission getAdmissionById(long id) {
        return admissionRepository.findById(id);
    }

    @Override
    public Admission save(long patient_id, long doctor_id, long room_id) {
        Patient patient = patientRepository.getPatientById(patient_id);
        Doctor doctor = doctorRepository.getDoctorById(doctor_id);
        Room room = roomRepository.getById(room_id);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        Admission admission=new Admission(patient,doctor,room,"ADMITTED",date);
        return admissionRepository.save(admission);
    }

    @Override
    public void deleteAdmissionById(long id) {
        admissionRepository.deleteById(id);
    }

    @Override
    public void saveAdmission(Admission admission) {
        admissionRepository.save(admission);
    }


}
