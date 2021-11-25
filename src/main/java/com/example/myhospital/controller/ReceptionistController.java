package com.example.myhospital.controller;

import com.example.myhospital.dto.ReceptionistRegistrationDto;
import com.example.myhospital.model.Receptionist;
import com.example.myhospital.model.User;
import com.example.myhospital.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReceptionistController {
    @Autowired
    private ReceptionistService receptionistService;


    public ReceptionistController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

//    @GetMapping("/")
//    public String viewHomePage(Model model){
//        model.addAttribute("listReceptionists",receptionistService.getAllReceptionists());
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
//        System.out.println(auth.getName());
//        return "home";
//    }

    @GetMapping("/receptionist/create")
    public String showNewReceptionistForm(Model model){
        model.addAttribute("receptionist",new ReceptionistRegistrationDto());
        model.addAttribute("update",false);
        return "create_receptionist";
    }

    @PostMapping("/receptionist/{update}/save")
    public String saveReceptionist(@ModelAttribute("receptionist") ReceptionistRegistrationDto registrationDto, @PathVariable("update") boolean update) {
        if(update){
            receptionistService.updateReceptionist(registrationDto);
        }
        else {
            receptionistService.save(registrationDto);
        }
        return "redirect:/home/admin";
    }
    @GetMapping("/receptionist/update/{id}")
    public String showUpdateForm(@PathVariable(value="id") long id, Model model){
        Receptionist receptionist=receptionistService.getReceptionistById(id);
        ReceptionistRegistrationDto receptionistRegistrationDto=new ReceptionistRegistrationDto(receptionist.getId(),receptionist.getUser().getFirst_name(),receptionist.getUser().getLast_name(),receptionist.getUser().getEmail(),receptionist.getUser().getPassword(),receptionist.getUser().getMobile_no(),receptionist.getUser().getGender(),receptionist.getUser().getAge(),receptionist.getDepartment(),receptionist.getSalary());
        model.addAttribute("receptionist",receptionistRegistrationDto);
        model.addAttribute("update",true);
        return "create_receptionist";
    }

    @GetMapping("/receptionist/delete/{id}")
    public String deleteReceptionist(@PathVariable(value="id") long id){
        this.receptionistService.deleteReceptionistById(id);
        return "redirect:/home/admin";
    }

    @GetMapping("/receptionist/{id}")
    public String viewReceptionist(@PathVariable long id,Model model){
        Receptionist receptionist=receptionistService.getReceptionistById(id);
        User user=receptionist.getUser();
        model.addAttribute("receptionist",receptionist);
        model.addAttribute("user",user);
        return "view_receptionist";
    }
    @GetMapping("/receptionist")
    public String listReceptionists(Model model){
        List<Receptionist> receptionistList=receptionistService.getAllReceptionists();
        model.addAttribute("receptionistList",receptionistList);
        return "receptionist_list";
    }
}
