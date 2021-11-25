package com.example.myhospital.model;

import javax.persistence.*;

@Table(name = "room")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private long cost;
    private long capacity;
    private long availability;

    @Column(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room(String type, long cost, long capacity, long availability, String name) {
        this.type = type;
        this.cost = cost;
        this.capacity = capacity;
        this.availability = availability;
        this.name = name;
    }

    public Room() {
    }

    public Room(String type, long cost, long capacity, long availability) {
        this.type = type;
        this.cost = cost;
        this.capacity = capacity;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getAvailability() {
        return availability;
    }

    public void setAvailability(long availability) {
        this.availability = availability;
    }
}