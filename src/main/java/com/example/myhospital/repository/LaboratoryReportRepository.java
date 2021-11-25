package com.example.myhospital.repository;

import com.example.myhospital.model.LaboratoryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryReportRepository extends JpaRepository<LaboratoryReport,Long> {
    LaboratoryReport findById(long id);
    List<LaboratoryReport> findAllByPatient_Id(long id);
    LaboratoryReport save(LaboratoryReport laboratoryReport);
}
