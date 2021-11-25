package com.example.myhospital.controller;

import com.example.myhospital.dto.DoctorRegistrationDto;
import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Patient;
import com.example.myhospital.model.User;
import com.example.myhospital.service.DoctorService;
import com.example.myhospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
//    @ModelAttribute("doctor")
//    public DoctorRegistrationDto doctorRegistrationDto() {
//        return new DoctorRegistrationDto();
//    }

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

//    @GetMapping("/")
//    public String viewHomePage(Model model){
//        model.addAttribute("listDoctors",doctorService.getAllDoctors());
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
//        System.out.println(auth.getName());
//        return "home";
//    }

    @GetMapping("/doctor/create")
    public String showNewDoctorForm(Model model){
        model.addAttribute("doctor",new DoctorRegistrationDto());
        model.addAttribute("update",false);
        return "create_doctor";
    }

    @PostMapping("/doctor/{update}/save")
    public String saveDoctor(@ModelAttribute("doctor") DoctorRegistrationDto registrationDto,@PathVariable("update") boolean update) {
        if(update){
            doctorService.updateDoctor(registrationDto);
        }
        else {
            doctorService.save(registrationDto);
        }
        return "redirect:/home/admin";
    }
    @GetMapping("/doctor/update/{id}")
    public String showUpdateForm(@PathVariable(value="id") long id, Model model){
        Doctor doctor=doctorService.getDoctorById(id);
        DoctorRegistrationDto doctorRegistrationDto=new DoctorRegistrationDto(doctor.getId(),doctor.getUser().getFirst_name(),doctor.getUser().getLast_name(),doctor.getUser().getEmail(),doctor.getUser().getPassword(),doctor.getUser().getMobile_no(),doctor.getUser().getGender(),doctor.getUser().getAge(),doctor.getDepartment(),doctor.getDesignation(),doctor.getSalary(),doctor.getQualification());
        model.addAttribute("doctor",doctorRegistrationDto);
        model.addAttribute("update",true);
        return "create_doctor";
    }

    @GetMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable(value="id") long id){
        this.doctorService.deleteDoctorById(id);
        return "redirect:/home/admin";
    }


//    @GetMapping("/requestAppointment")
//    public String requestAppointment(Model model){
//        model.addAttribute("list_doctors",doctorService.getAllDoctors());
//        return "doctors-appointment";
//    }

    @GetMapping("/doctor/{id}")
    public String viewDoctor(@PathVariable long id, Model model){
        Doctor doctor=doctorService.getDoctorById(id);
        User user=doctor.getUser();
        model.addAttribute("doctor",doctor);
        model.addAttribute("user",user);
        return "view_doctor";
    }
    @GetMapping("/requestAppointment/{id}")
    public String requestAppointment(Model model,@PathVariable long id) {
        List<Doctor> doctorsList = doctorService.getAllDoctors();
        System.out.println(doctorsList.size());
        model.addAttribute("doctorsList", doctorsList);
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("isPatient",true);
        return "doctor_list";
    }
    @GetMapping("/doctor/search")
    public String doctorList(@RequestParam("department") String department,Model model){
        model.addAttribute("isPatient",false);
        List<Doctor> doctorsList=doctorService.getDoctorsByDepartment(department);
        model.addAttribute("doctorsList",doctorsList);
        model.addAttribute("patient",null);
        return "doctor_list";
    }
}
