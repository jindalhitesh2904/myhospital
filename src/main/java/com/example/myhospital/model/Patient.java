package com.example.myhospital.model;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "patient")
@Entity
public class Patient /*extends User*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    private Collection<LaboratoryReport> laboratory_reports;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    private Collection<Admission> admissions;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    private Collection<Appointment> appointments;

    public Patient() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<LaboratoryReport> getLaboratory_reports() {
        return laboratory_reports;
    }

    public void setLaboratory_reports(Collection<LaboratoryReport> laboratory_reports) {
        this.laboratory_reports = laboratory_reports;
    }

    public Collection<Admission> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(Collection<Admission> admissions) {
        this.admissions = admissions;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Patient(User user) {
        this.user = user;
    }
}