package com.example.myhospital.repository;

import com.example.myhospital.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends JpaRepository<Charge,Long> {
    Charge findByName(String name);
}
