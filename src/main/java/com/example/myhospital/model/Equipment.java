package com.example.myhospital.model;

import javax.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String description;
    private long total_quantity;
    @Column(nullable = true)
    private long available_quantity;

    public Equipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(long total_quantity) {
        this.total_quantity = total_quantity;
    }

    public long getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(long available_quantity) {
        this.available_quantity = available_quantity;
    }

    public Equipment(String description, long total_quantity) {
        this.description = description;
        this.total_quantity = total_quantity;
    }

    public Equipment(String description, long total_quantity, long available_quantity) {
        this.description = description;
        this.total_quantity = total_quantity;
        this.available_quantity = available_quantity;
    }
}
