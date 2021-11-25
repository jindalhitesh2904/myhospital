package com.example.myhospital.dto;

import javax.persistence.Column;

public class DoctorRegistrationDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String qualification;

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public DoctorRegistrationDto(Long id, String first_name, String last_name, String email, String password, String mobile_no, String gender, long age, String department, String designation, long salary, String qualification) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_no = mobile_no;
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.qualification=qualification;
    }

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String mobile_no;
    private String gender;
    private long age;
    private String department;
    private String designation;

    public DoctorRegistrationDto() {
    }

    private long salary;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public DoctorRegistrationDto(String first_name, String last_name, String email, String password, String mobile_no, String gender, long age, String department, String designation, long salary) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_no = mobile_no;
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }
}
