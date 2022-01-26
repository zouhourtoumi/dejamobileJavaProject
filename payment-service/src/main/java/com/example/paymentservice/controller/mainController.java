package com.example.paymentservice.controller;

import com.example.paymentservice.bank.BankAccount;
import com.example.paymentservice.bank.Transaction;
import com.example.paymentservice.payment.Payment;
import com.example.paymentservice.service.BankService;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.service.TransactionService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class mainController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BankService bankService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping(path ="/payments/{id}" )
    public List<Payment> paymentList(@PathVariable Long id){
        Payment payment = new Payment();
        List<Payment> list = new ArrayList<>();

        List<Transaction> transactionList= transactionService.transactionList(id);

        for (Transaction transaction : transactionList) {
            if(transactionList.isEmpty()==false) {
                Date date = transaction.getTransactionDate();
                Double doubl = transaction.getMontant();
                list.add(new Payment(null,doubl,date));
            }
        }
        return list;
    }


}

