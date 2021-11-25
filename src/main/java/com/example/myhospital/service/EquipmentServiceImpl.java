package com.example.myhospital.service;

import com.example.myhospital.model.Equipment;
import com.example.myhospital.model.Room;
import com.example.myhospital.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Override
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment getEquipmentById(long id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        long id=equipment.getId();
        Equipment equipment1 = equipmentRepository.findById(id);
        equipment1.setDescription(equipment.getDescription());
        equipment1.setTotal_quantity(equipment.getTotal_quantity());
        equipment1.setAvailable_quantity(equipment.getAvailable_quantity());
        return equipmentRepository.save(equipment1);
    }
}
