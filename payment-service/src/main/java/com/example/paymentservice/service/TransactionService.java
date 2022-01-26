package com.example.paymentservice.service;

import com.example.paymentservice.bank.BankAccountRepository;
import com.example.paymentservice.bank.Transaction;
import com.example.paymentservice.bank.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<Transaction> transactionList(Long id){
            return transactionRepository.findAllByBankAccount(bankAccountRepository.findById(id));
    }
}
