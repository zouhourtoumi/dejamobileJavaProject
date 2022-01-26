package com.example.paymentservice.bank;

import com.example.paymentservice.bank.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BankAccountRepository extends JpaRepository <BankAccount,Long> {

    List<BankAccount> findAll();
}
