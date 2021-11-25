package com.example.myhospital.controller;

import com.example.myhospital.model.Equipment;
import com.example.myhospital.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

//    @ModelAttribute("equipment")
//    public Equipment equipmentRegistrationDto() {
//        return new Equipment();
//    }

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

//    @GetMapping("/")
//    public String viewHomePage(Model model){
//        model.addAttribute("listEquipments",equipmentService.getAllEquipments());
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
//        System.out.println(auth.getName());
//        return "home";
//    }

    @GetMapping("/equipment/create")
    public String showNewEquipmentForm(Model model){
        model.addAttribute("equipment",new Equipment());
        model.addAttribute("update",false);
        return "create_equipment";
    }

    @PostMapping("/equipment/{update}/save")
    public String saveEquipment(@ModelAttribute("equipment") Equipment equipment, @PathVariable("update") boolean update) {
        if(update){
            equipmentService.updateEquipment(equipment);
        }
        else {
            equipmentService.save(equipment);
        }
        return "redirect:/home/admin";
    }
    @GetMapping("/equipment/update/{id}")
    public String showUpdateForm(@PathVariable(value="id") long id, Model model){
        Equipment equipment=equipmentService.getEquipmentById(id);
        model.addAttribute("equipment",equipment);
        model.addAttribute("update",true);
        return "create_equipment";
    }
}
