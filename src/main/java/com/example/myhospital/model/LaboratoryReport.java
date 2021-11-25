package com.example.myhospital.model;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "laboratory_report")
@Entity
public class LaboratoryReport {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;

    private String name;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LaboratoryReport(Patient patient, Doctor doctor, String name, Date date, String findings) {
        this.patient = patient;
        this.doctor = doctor;
        this.name = name;
        this.date = date;
        this.findings = findings;
    }

    private Date date;

    private String findings;

    public LaboratoryReport() {

    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LaboratoryReport(Patient patient, Doctor doctor, String name, String findings) {
        this.patient = patient;
        this.doctor = doctor;
        this.name = name;
        this.findings = findings;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}