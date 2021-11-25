package com.example.myhospital.repository;

import com.example.myhospital.model.Doctor;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
//    Doctor findByEmail(String email);
    Doctor getDoctorById(long id);
    Doctor getDoctorByUser_Id(long id);
    List<Doctor> findAllByDepartment(String department);
}

