package com.example.myhospital.repository;

import com.example.myhospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient getPatientById(long id);
    Patient findByUser_Id(long id);
}
