package com.example.myhospital.repository;

import com.example.myhospital.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
    Equipment findById(long id);
}
