package com.example.myhospital.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_ID", nullable = false)
    private Patient patient;
    private Date bill_date;

    public Date getBill_date() {
        return bill_date;
    }

    public void setBill_date(Date bill_date) {
        this.bill_date = bill_date;
    }
    private long amount_paid;

    public long getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(long amount_paid) {
        this.amount_paid = amount_paid;
    }

    @ManyToMany
    @JoinTable(name = "bill_charges",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "charge_id", referencedColumnName = "id")
    )
    private List<Charge> charges;

    private long amount;

    public Bill(List<Charge> charges, long amount) {
        this.charges = charges;
        this.amount = amount;
    }

    public Bill(Patient patient, List<Charge> charges, long amount) {
        this.patient = patient;
        this.charges = charges;
        this.amount = amount;
    }

    public Bill(Patient patient) {
        this.patient = patient;
    }

    public Bill(List<Charge> charges) {
        this.charges = charges;
    }

    public Bill() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
