package com.example.myhospital.controller;

import com.example.myhospital.model.Bill;
import com.example.myhospital.model.Charge;
import com.example.myhospital.model.Patient;
import com.example.myhospital.service.BillService;
import com.example.myhospital.service.ChargeService;
import com.example.myhospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private ChargeService chargeService;
    @Autowired
    private PatientService patientService;
    @GetMapping("/bill/create/{id}")
    public String createBill(@PathVariable long id, Model model){
        List<Charge> chargeList = chargeService.getAllCharges();
        model.addAttribute("chargeList",chargeList);
        Patient patient=patientService.getPatientById(id);
        model.addAttribute("patient",patient);
        return "create_bill";
    }
    @PostMapping("/bill/save/{id}")
    public String saveBill(@RequestParam("charges") List<String> charges,@PathVariable long id){
        Patient patient=patientService.getPatientById(id);
        List<Charge> chargeList = new ArrayList<>();
        for(String name:charges){
            Charge charge=chargeService.findByName(name);
            chargeList.add(charge);
        }
        Bill bill=new Bill();
        bill.setCharges(chargeList);
        bill.setPatient(patient);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        bill.setBill_date(date);
        bill.setAmount_paid(0);
        long amount=0;
        for(Charge charge:chargeList){
            amount=amount+charge.getCost();
        }
        bill.setAmount(amount);
        billService.save(bill);
        return "redirect:/home/nurse";
    }
    @GetMapping("/bill/all/{patient_id}")
    public String BillPatient(Model model,@PathVariable long patient_id){
        List<Bill> billList=billService.getAllBillsByPatient_Id(patient_id);
        model.addAttribute("billList",billList);
        return "patient_bills";
    }
    @GetMapping("bill/view/{id}")
    public String viewBill(@PathVariable long id,Model model){
        Bill bill=billService.getBillById(id);
        model.addAttribute("bill",bill);
        return "view_bill";
    }
    @GetMapping("/bill/pay")
    public String payBill(Model model,@RequestParam("billId") long bill_id){
        Bill bill = billService.getBillById(bill_id);
        model.addAttribute("bill",bill);
        return "pay_bill";
    }
    @PostMapping("/bill/pay/{id}")
    public String enterAmount(@PathVariable long id,@RequestParam("payAmount") long payAmount){
        Bill bill=billService.getBillById(id);
        long prevAmount= bill.getAmount_paid();
        prevAmount=prevAmount+payAmount;
        bill.setAmount_paid(prevAmount);
        billService.save(bill);
        return "redirect:/home/receptionist";
    }
}
