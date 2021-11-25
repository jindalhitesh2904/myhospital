package com.example.myhospital.controller;

import com.example.myhospital.model.Room;
import com.example.myhospital.model.User;
import com.example.myhospital.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

//    @ModelAttribute("room")
//    public Room roomRegistrationDto() {
//        return new Room();
//    }

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

//    @GetMapping("/")
//    public String viewHomePage(Model model){
//        model.addAttribute("listRooms",roomService.getAllRooms());
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
//        System.out.println(auth.getName());
//        return "home";
//    }

    @GetMapping("/room/create")
    public String showNewRoomForm(Model model){
        model.addAttribute("room",new Room());
        model.addAttribute("update",false);
        return "create_room";
    }

    @PostMapping("/room/{update}/save")
    public String saveRoom(@ModelAttribute("room") Room room, @PathVariable("update") boolean update) {
        if(update){
            roomService.updateRoom(room);
        }
        else {
            roomService.save(room);
        }
        return "redirect:/home/admin";
    }
    @GetMapping("/room/update/{id}")
    public String showUpdateForm(@PathVariable(value="id") long id, Model model){
        Room room=roomService.getRoomById(id);
        model.addAttribute("room",room);
        model.addAttribute("update",true);
        return "create_room";
    }

    @GetMapping("/room/delete/{id}")
    public String deleteRoom(@PathVariable(value="id") long id){
        this.roomService.deleteRoomById(id);
        return "redirect:/home/admin";
    }


//    @GetMapping("/requestAppointment")
//    public String requestAppointment(Model model){
//        model.addAttribute("list_rooms",roomService.getAllRooms());
//        return "rooms-appointment";
//    }

}
