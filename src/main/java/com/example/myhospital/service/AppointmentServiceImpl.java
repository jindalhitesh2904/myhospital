package com.example.myhospital.service;

import com.example.myhospital.controller.PatientController;
import com.example.myhospital.model.Appointment;
import com.example.myhospital.model.Doctor;
import com.example.myhospital.model.Patient;
import com.example.myhospital.repository.AppointmentRepository;
import com.example.myhospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Appointment> getAppointmentsByDoctor_IdAndStatus(long id, String status) {
        return appointmentRepository.findAllByDoctor_IdAndStatus(id,status);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient_Id(long id) {
        return appointmentRepository.findAllByPatient_Id(id);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor_Id(long id) {
        return appointmentRepository.findAllByDoctor_Id(id);
    }

    @Override
    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findAllByStatus(status);
    }

    @Override
    public Appointment getAppointmentById(long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment save(Date date, Time time, Doctor doctor,long patient_id) {
        Patient patient = patientRepository.getPatientById(patient_id);
        Appointment appointment = new Appointment("REQUEST PENDING",date,time,patient,doctor);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment updateAppointment(long id, Date date, Time time) {
        Appointment appointment= appointmentRepository.findById(id);
        appointment.setAppointment_date(date);
        appointment.setAppointment_time(time);
        appointment.setStatus("SCHEDULED");
        return appointmentRepository.save(appointment);
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointmentsByPatient_IdAndStatus(long id, String status) {
        return appointmentRepository.findAllByPatient_IdAndStatus(id,status);
    }
}
