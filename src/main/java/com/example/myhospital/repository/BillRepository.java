package com.example.myhospital.repository;

import com.example.myhospital.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findAllByPatient_Id(long id);
    Bill findById(long id);
}
