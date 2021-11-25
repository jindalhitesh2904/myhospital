package com.example.myhospital.repository;

import com.example.myhospital.model.Nurse;
import com.example.myhospital.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist,Long> {
    Receptionist getReceptionistById(long id);
}
