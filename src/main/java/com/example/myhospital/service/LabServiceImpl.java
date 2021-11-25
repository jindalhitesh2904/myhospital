package com.example.myhospital.service;

import com.example.myhospital.model.LaboratoryReport;
import com.example.myhospital.repository.LaboratoryReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabServiceImpl implements LabService{
    @Autowired
    private LaboratoryReportRepository laboratoryReportRepository;
    @Override
    public LaboratoryReport getReportById(long id) {
        return laboratoryReportRepository.findById(id);
    }

    @Override
    public List<LaboratoryReport> getReportsByPatientId(long id) {
        return laboratoryReportRepository.findAllByPatient_Id(id);
    }

    @Override
    public LaboratoryReport save(LaboratoryReport laboratoryReport) {
        return laboratoryReportRepository.save(laboratoryReport);
    }
}
