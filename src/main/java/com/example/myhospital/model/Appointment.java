package com.example.myhospital.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Table(name = "appointment")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private String status;

    //    @Temporal(TemporalType.DATE)
    private Date appointment_date;

    //    @Temporal(TemporalType.TIME)
    private Time appointment_time;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_ID", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public Time getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(Time appointment_time) {
        this.appointment_time = appointment_time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Appointment(String status, Date appointment_date, Time appointment_time, Patient patient, Doctor doctor) {
        this.status = status;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.patient = patient;
        this.doctor = doctor;
    }
}