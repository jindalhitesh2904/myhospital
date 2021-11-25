package com.example.myhospital.service;

import com.example.myhospital.model.Bill;

import java.util.List;

public interface BillService {
    Bill save(Bill bill);
    List<Bill> getAllBillsByPatient_Id(long id);
    Bill getBillById(long id);
}
