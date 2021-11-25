package com.example.myhospital.service;

import com.example.myhospital.dto.NurseRegistrationDto;
import com.example.myhospital.dto.ReceptionistRegistrationDto;
import com.example.myhospital.model.Nurse;
import com.example.myhospital.model.Receptionist;

import java.util.List;

public interface ReceptionistService {
    List<Receptionist> getAllReceptionists();
    Receptionist save(ReceptionistRegistrationDto registrationDto);
    Receptionist getReceptionistById(long id);
    void deleteReceptionistById(long id);
    Receptionist updateReceptionist(ReceptionistRegistrationDto registrationDto);
}
