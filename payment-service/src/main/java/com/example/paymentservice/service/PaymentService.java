package com.example.paymentservice.service;

import com.example.paymentservice.bank.Transaction;
import com.example.paymentservice.payment.PaymentRepository;
import com.example.paymentservice.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    public List<Payment> paymentList(){
        return paymentRepository.findAll();
    }

    public Payment saveToDB(Payment payment){
        return  paymentRepository.save(payment);
    }


}

