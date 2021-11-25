package com.example.myhospital.controller;

import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.LaboratoryReport;
import com.example.myhospital.model.Patient;
import com.example.myhospital.model.User;
import com.example.myhospital.repository.UserRepository;
import com.example.myhospital.service.DoctorService;
import com.example.myhospital.service.LabService;
import com.example.myhospital.service.PatientService;
import com.example.myhospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class LaboratoryReportController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;
    @Autowired
    private LabService labService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/labReport/create/{id}")
    public String createReport(Model model,@PathVariable long id){
        Patient patient=patientService.getPatientById(id);
        model.addAttribute("patient",patient);
        return "create_laboratoryReport";
    }
    @PostMapping("/labReport/{id}/save")
    public String saveReport(@PathVariable long id,@RequestParam("findings") String findings, @RequestParam String test_name){
        LaboratoryReport laboratoryReport=new LaboratoryReport();
        Patient patient=patientService.getPatientById(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user=userRepository.findByEmail(username);
        Doctor doctor=doctorService.getDoctorByUser_Id(user.getId());
        laboratoryReport.setDoctor(doctor);
        laboratoryReport.setPatient(patient);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        laboratoryReport.setDate(date);
        laboratoryReport.setFindings(findings);
        laboratoryReport.setName(test_name);
        labService.save(laboratoryReport);
        return "redirect:/home/doctor";
    }
    @GetMapping("/labReport/all/{patient_id}")
    public String myLabReports(@PathVariable long patient_id,Model model){
        Patient patient=patientService.getPatientById(patient_id);
        model.addAttribute("patient",patient);
        return "my_labreports";
    }
}
