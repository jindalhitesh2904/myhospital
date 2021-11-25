package com.example.myhospital.service;

import com.example.myhospital.model.Charge;
import com.example.myhospital.repository.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService{

    @Autowired
    private ChargeRepository chargeRepository;
    @Override
    public Charge save(Charge charge) {
        return chargeRepository.save(charge);
    }

    @Override
    public List<Charge> getAllCharges() {
        return chargeRepository.findAll();
    }

    @Override
    public Charge findByName(String name) {
        return chargeRepository.findByName(name);
    }
}
