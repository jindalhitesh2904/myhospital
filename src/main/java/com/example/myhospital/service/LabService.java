package com.example.myhospital.service;

import com.example.myhospital.model.LaboratoryReport;

import java.util.List;

public interface LabService {
    LaboratoryReport getReportById(long id);
    List<LaboratoryReport> getReportsByPatientId(long id);
    LaboratoryReport save(LaboratoryReport laboratoryReport);
}
