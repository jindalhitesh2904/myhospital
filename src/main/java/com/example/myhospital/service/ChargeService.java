package com.example.myhospital.service;

import com.example.myhospital.model.Charge;

import java.util.List;

public interface ChargeService {
    Charge save(Charge charge);
    List<Charge> getAllCharges();
    Charge findByName(String name);
}
