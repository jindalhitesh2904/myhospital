package com.example.myhospital.controller;

import com.example.myhospital.dto.PatientRegistrationDto;
import com.example.myhospital.model.Patient;
import com.example.myhospital.model.User;
import com.example.myhospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/create")
    public String showNewPatientForm(Model model){
        model.addAttribute("patient",new PatientRegistrationDto());
        model.addAttribute("update",false);
        return "create_patient";
    }

    @PostMapping("/patient/{update}/save")
    public String savePatient(@ModelAttribute("patient") PatientRegistrationDto registrationDto, @PathVariable("update") boolean update) {
        if(update){
            patientService.updatePatient(registrationDto);
        }
        else {
            patientService.save(registrationDto);
        }
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        boolean isAdmin=auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ADMIN"));
        boolean isReceptionist=auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("RECEPTIONIST"));
        if(isAdmin)
            return "redirect:/home/admin";
        if(isReceptionist)
            return "redirect:/home/receptionist";
        return "redirect:/";
    }
    @GetMapping("/patient/{update}/{id}")
    public String showUpdateForm(@PathVariable(value="id") long id, Model model){
        Patient patient=patientService.getPatientById(id);
        PatientRegistrationDto patientRegistrationDto=new PatientRegistrationDto(patient.getId(),patient.getUser().getFirst_name(),patient.getUser().getLast_name(),patient.getUser().getEmail(),patient.getUser().getPassword(),patient.getUser().getMobile_no(),patient.getUser().getGender(),patient.getUser().getAge());
        model.addAttribute("patient",patientRegistrationDto);
        model.addAttribute("update",true);
        return "create_patient";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable(value="id") long id){
        this.patientService.deletePatientById(id);
        return "redirect:/home/admin";
    }


//    @GetMapping("/requestAppointment")
//    public String requestAppointment(Model model){
//        model.addAttribute("list_patients",patientService.getAllPatients());
//        return "patients-appointment";
//    }

    @GetMapping("/patient/{id}")
    public String viewPatient(@PathVariable long id, Model model){
        Patient patient=patientService.getPatientById(id);
        User user=patient.getUser();
        model.addAttribute("patient",patient);
        model.addAttribute("user",user);
        return "view_patient";
    }
//    @GetMapping("/view/patients")
//    public String requestAppointment(Model model){
//        List<Patient> patientList = patientService.getAllPatients();
//        model.addAttribute("patientList",patientList);
//        return "patient_list";
//    }
    @GetMapping("/patient/search")
    public String searchPatient(@RequestParam("email") String email,Model model){
        Patient patient= patientService.getPatientByEmail(email);
        model.addAttribute("patient",patient);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        boolean isNurse=auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("NURSE"));
        boolean isDoctor=auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("DOCTOR"));
        model.addAttribute("isNurse",isNurse);
        model.addAttribute("isDoctor",isDoctor);
        return "patient_searched";
    }
}
