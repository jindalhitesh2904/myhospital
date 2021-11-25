package com.example.myhospital.service;

import com.example.myhospital.model.Bill;
import com.example.myhospital.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;
    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getAllBillsByPatient_Id(long id) {
        return billRepository.findAllByPatient_Id(id);
    }

    @Override
    public Bill getBillById(long id) {
        return billRepository.findById(id);
    }

}
