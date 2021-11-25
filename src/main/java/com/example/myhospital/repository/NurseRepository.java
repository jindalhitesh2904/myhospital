package com.example.myhospital.repository;

import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<Nurse,Long> {
    Nurse getNurseById(long id);
}
