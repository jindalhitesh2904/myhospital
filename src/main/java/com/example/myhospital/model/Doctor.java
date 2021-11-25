package com.example.myhospital.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Doctor /*extends User*/{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long salary;
    private String designation;
//    private String specialization;
    private String department;
    private String qualification;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    public Doctor(String first_name, String last_name, String email, String password, String mobile_no, String gender, long age, Collection<Role> roles, long salary, String designation, String department) {
//        super(first_name, last_name, email, password, mobile_no, gender, age, roles);
//        this.salary = salary;
//        this.designation = designation;
//        this.department = department;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "doctor")
    private Collection<Appointment> appointments;

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Collection<Admission> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(Collection<Admission> admissions) {
        this.admissions = admissions;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "doctor")
    private Collection<Admission> admissions;

    public Doctor(User user,long salary, String designation, String department,String qualification) {
        this.salary = salary;
        this.designation = designation;
        this.department = department;
        this.user = user;
        this.qualification=qualification;
    }

    public Doctor() {}

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
