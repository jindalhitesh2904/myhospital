package com.example.myhospital.service;

import com.example.myhospital.model.Appointment;
import com.example.myhospital.model.Doctor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsByDoctor_IdAndStatus(long id,String status);
    List<Appointment> getAppointmentsByPatient_Id(long id);
    List<Appointment> getAppointmentsByDoctor_Id(long id);
    List<Appointment> getAppointmentsByStatus(String status);
    Appointment getAppointmentById(long id);
    Appointment save(Date date, Time time, Doctor doctor,long patient_id);
    void deleteAppointmentById(long id);
    Appointment updateAppointment(long id,Date date,Time time);

    void saveAppointment(Appointment appointment);
    List<Appointment> getAllAppointmentsByPatient_IdAndStatus(long id,String status);
}
