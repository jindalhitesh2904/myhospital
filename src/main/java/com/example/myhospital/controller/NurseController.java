package com.example.myhospital.controller;

import com.example.myhospital.dto.DoctorRegistrationDto;
import com.example.myhospital.dto.NurseRegistrationDto;
import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Nurse;
import com.example.myhospital.model.User;
import com.example.myhospital.service.DoctorService;
import com.example.myhospital.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NurseController {

    @Autowired
    private NurseService nurseService;
    
    
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

//    @GetMapping("/")
//    public String viewHomePage(Model model){
//        model.addAttribute("listNurses",nurseService.getAllNurses());
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
//        System.out.println(auth.getName());
//        return "home";
//    }

    @GetMapping("/nurse/create")
    public String showNewNurseForm(Model model){
        model.addAttribute("nurse",new NurseRegistrationDto());
        model.addAttribute("update",false);
        return "create_nurse";
    }

    @PostMapping("/nurse/{update}/save")
    public String saveNurse(@ModelAttribute("nurse") NurseRegistrationDto registrationDto, @PathVariable("update") boolean update) {
        if(update){
            nurseService.updateNurse(registrationDto);
        }
        else {
            nurseService.save(registrationDto);
        }
        return "redirect:/home/admin";
    }
    @GetMapping("/nurse/update/{id}")
    public String showUpdateForm(@PathVariable(value="id") long id, Model model){
        Nurse nurse=nurseService.getNurseById(id);
        NurseRegistrationDto nurseRegistrationDto=new NurseRegistrationDto(nurse.getId(),nurse.getUser().getFirst_name(),nurse.getUser().getLast_name(),nurse.getUser().getEmail(),nurse.getUser().getPassword(),nurse.getUser().getMobile_no(),nurse.getUser().getGender(),nurse.getUser().getAge(),nurse.getSalary());
        model.addAttribute("nurse",nurseRegistrationDto);
        model.addAttribute("update",true);
        return "create_nurse";
    }

    @GetMapping("/nurse/delete/{id}")
    public String deleteNurse(@PathVariable(value="id") long id){
        this.nurseService.deleteNurseById(id);
        return "redirect:/home/admin";
    }

    @GetMapping("/nurse/{id}")
    public String viewNurse(@PathVariable long id,Model model){
        Nurse nurse=nurseService.getNurseById(id);
        User user=nurse.getUser();
        model.addAttribute("nurse",nurse);
        model.addAttribute("user",user);
        return "view_nurse";
    }
    @GetMapping("/nurse")
    public String listNurses(Model model){
        List<Nurse> nurseList=nurseService.getAllNurses();
        model.addAttribute("nurseList",nurseList);
        return "nurse_list";
    }
}
