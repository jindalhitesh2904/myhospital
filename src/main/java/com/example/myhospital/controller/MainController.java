package com.example.myhospital.controller;

import com.example.myhospital.model.*;
import com.example.myhospital.repository.AdminRepository;
import com.example.myhospital.repository.UserRepository;
import com.example.myhospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReceptionistService receptionistService;

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/home/admin")
    public String homeAdmin(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
//        User user=userRepository.findByEmail(username);
//        System.out.println(user.getRoles());
        return "admin_dashboard";
    }

    @GetMapping("/home/nurse")
    public String homeNurse(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
//        User user=userRepository.findByEmail(username);
//        System.out.println(user.getRoles());
        return "nurse_dashboard";
    }

    @GetMapping("/home/receptionist")
    public String homeReceptionist(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
//        User user=userRepository.findByEmail(username);
//        System.out.println(user.getRoles());
        return "receptionist_dashboard";
    }
    @GetMapping("/home/doctor")
    public String homeDoctor(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
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
        model.addAttribute("doctor",doctor);
        return "doctor_dashboard";
    }
    @GetMapping("/home/patient")
    public String homePatient(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user=userRepository.findByEmail(username);
        Patient patient=patientService.getPatientByUser_Id(user.getId());
        List<Appointment> appointmentList=appointmentService.getAllAppointmentsByPatient_IdAndStatus(patient.getId(),"SCHEDULED");
        model.addAttribute("appointmentList",appointmentList);
        model.addAttribute("patient",patient);
        return "patient_dashboard";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/staff")
    public String listStaff(Model model){
        List<Doctor> doctorList=doctorService.getAllDoctors();
        List<Nurse> nurseList=nurseService.getAllNurses();
        List<Receptionist> receptionistList = receptionistService.getAllReceptionists();
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("receptionistList",receptionistList);
        model.addAttribute("nurseList",nurseList);
        return "staff_list";
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }

}
