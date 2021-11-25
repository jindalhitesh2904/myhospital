package com.example.myhospital.service;

import com.example.myhospital.model.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> getAllEquipments();
    Equipment save(Equipment equipment);
    Equipment getEquipmentById(long id);
    Equipment updateEquipment(Equipment equipment);
}
