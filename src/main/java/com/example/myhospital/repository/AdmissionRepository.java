package com.example.myhospital.repository;

import com.example.myhospital.model.Admission;
import com.example.myhospital.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission,Long> {
    List<Admission> findAllByDoctor_IdAndStatus(long id, String status);
    List<Admission> findAllByDoctor_Id(long id);
    List<Admission> findAllByStatus(String status);
    List<Admission> findAllByPatient_Id(long id);
    Admission save(Admission admission);
    Admission findById(long id);
}
