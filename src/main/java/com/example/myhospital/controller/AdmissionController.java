package com.example.myhospital.controller;

import com.example.myhospital.model.Admission;
import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Patient;
import com.example.myhospital.model.Room;
import com.example.myhospital.service.AdmissionService;
import com.example.myhospital.service.DoctorService;
import com.example.myhospital.service.PatientService;
import com.example.myhospital.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class AdmissionController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AdmissionService admissionService;
    @Autowired
    private RoomService roomService;

    @GetMapping("/admitPatient")
    public String admitPatient(Model model){
        List<Patient> patientList = patientService.getAllPatients();
        List<Doctor> doctorList = doctorService.getAllDoctors();
        List<Room> roomList = roomService.getAllRooms();
        model.addAttribute("patientList",patientList);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("roomList",roomList);
        return "admit_patient";
    }

    @PostMapping("/admitPatient")
    public String makeAdmission(@RequestParam long patientId,@RequestParam long doctorId,@RequestParam long roomId){
        admissionService.save(patientId,doctorId,roomId);
        return "redirect:/home/receptionist";
    }
    @GetMapping("/admission/admitted/{id}")
    public String admittedPatients(Model model, @PathVariable long id){
        List<Admission> admissionList=admissionService.getAdmissionsByDoctor_IdAndStatus(id,"ADMITTED");
        model.addAttribute("admissionList",admissionList);
        return "myadmitted_patients";
    }
    @GetMapping("/admission/discharge/{id}")
    public String dischargePatient(Model model,@PathVariable long id){
        Admission admission=admissionService.getAdmissionById(id);
        model.addAttribute("admission",admission);
        return "discharge_patient";
    }
    @PostMapping("/admission/discharge/{id}")
    public String dischargePatient(@PathVariable long id,@RequestParam("dischargeSummary") String dischargeSummary){
        Admission admission=admissionService.getAdmissionById(id);
        admission.setStatus("DISCHARGED");
        admission.setDischarge_summary(dischargeSummary);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        admission.setDischarge_date(date);
        admissionService.saveAdmission(admission);
        return "redirect:/admission/admitted/{id}";
    }

    @GetMapping("/admission/all/{patient_id}")
    public String myAdmissions(Model model,@PathVariable long patient_id){
        Patient patient=patientService.getPatientById(patient_id);
        model.addAttribute("patient",patient);
        return "my_admissions";
    }
}
