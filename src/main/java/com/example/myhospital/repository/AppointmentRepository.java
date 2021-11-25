package com.example.myhospital.repository;

import com.example.myhospital.model.Appointment;
import com.example.myhospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findAllByDoctor_IdAndStatus(long id, String status);
    List<Appointment> findAllByDoctor_Id(long id);
    List<Appointment> findAllByStatus(String status);
    List<Appointment> findAllByPatient_Id(long id);
    Appointment save(Appointment appointment);
    Appointment findById(long id);
    List<Appointment> findAllByPatient_IdAndStatus(long id,String status);
}
