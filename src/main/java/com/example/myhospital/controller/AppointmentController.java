package com.example.myhospital.controller;

import com.example.myhospital.model.Appointment;
import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Patient;
import com.example.myhospital.service.AppointmentService;
import com.example.myhospital.service.DoctorService;
import com.example.myhospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    //for patient
    @GetMapping("/requestAppointment/{doctor_id}/{patient_id}")
    public String requestAppointmentWithDoctor(@PathVariable long doctor_id,@PathVariable long patient_id, Model model){
        Doctor doctor = doctorService.getDoctorById(doctor_id);
        Patient patient = patientService.getPatientById(patient_id);
        model.addAttribute("doctor",doctor);
        model.addAttribute("patient",patient);
        Appointment appointment= new Appointment();
        model.addAttribute("appointment",appointment);
        model.addAttribute("isPatient",true);
        return "request_appointment";
    }
    //for patient
    @PostMapping("/appointment/save/{doctor_id}/{patient_id}")
    public String saveAppointmentWithDoctor(@PathVariable long doctor_id, @PathVariable long patient_id, Model model, @RequestParam("appointment_date") String date, @RequestParam("appointment_time") String time){
        Doctor doctor = doctorService.getDoctorById(doctor_id);
        Date appointment_date=Date.valueOf(date);
        Time appointment_time=Time.valueOf(time+":00");
        appointmentService.save(appointment_date,appointment_time,doctor,patient_id);
        return "redirect:/home/patient";
    }
    //for receptionist
    @GetMapping("/appointment/schedule/{id}")
    public String scheduleAppointmentForm(@PathVariable long id,Model model){
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment",appointment);
        model.addAttribute("isPatient",false);
        return "request_appointment";
    }

    //for receptionist
    @PostMapping("/appointment/schedule/{id}")
    public String scheduleAppointment(@PathVariable long id, @RequestParam("appointment_date") String date, @RequestParam("appointment_time") String time){
        Date appointment_date=Date.valueOf(date);
        Time appointment_time=Time.valueOf(time);
        appointmentService.updateAppointment(id,appointment_date,appointment_time);
        return "redirect:/home/receptionist";
    }

    //for receptionist
    @GetMapping("/appointment/pending")
    public String requestPendingAppointments(Model model){
        List<Appointment> appointmentList=appointmentService.getAppointmentsByStatus("REQUEST PENDING");
        model.addAttribute("appointmentList",appointmentList);
        return "pending_appointments";
    }

    @GetMapping("/appointment/scheduled/{doctor_id}")
    public String myPendingAppointments(@PathVariable long doctor_id,Model model){
        List<Appointment> appointmentList = appointmentService.getAppointmentsByDoctor_IdAndStatus(doctor_id,"SCHEDULED");
        model.addAttribute("appointmentList",appointmentList);
        return "mypending_appointments";
    }
    @GetMapping("/appointment/complete/{id}")
    public String completeAppointment(@PathVariable long id,Model model){
        Appointment appointment=appointmentService.getAppointmentById(id);
        model.addAttribute("appointment",appointment);
        return "complete_appointment";
    }

    @PostMapping("/appointment/complete/{id}")
    public String addPrescription(@PathVariable long id,@RequestParam("summary") String summary){
        Appointment appointment=appointmentService.getAppointmentById(id);
        appointment.setSummary(summary);
        appointment.setStatus("COMPLETED");
        appointmentService.saveAppointment(appointment);
        return "redirect:/home/doctor";
    }

    @GetMapping("/appointment/all/{patient_id}")
    public String myAdmissions(Model model,@PathVariable long patient_id){
        Patient patient=patientService.getPatientById(patient_id);
        model.addAttribute("patient",patient);
        return "my_appointments";
    }
}
