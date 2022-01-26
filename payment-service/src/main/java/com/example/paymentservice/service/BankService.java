package com.example.paymentservice.service;

import com.example.paymentservice.bank.BankAccountRepository;
import com.example.paymentservice.bank.BankAccount;
import com.example.paymentservice.bank.Transaction;
import com.example.paymentservice.bank.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.table.TableCellRenderer;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> bankAccountList(){
            return bankAccountRepository.findAll();
    }



}
