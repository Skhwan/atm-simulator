package com.controller;

import com.database.BankJdbcRepository;
import com.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */

@Component
public class BankCalculator {

    @Autowired
    BankJdbcRepository bankJdbcRepository;

    public int calculateBank(int amount){
        List<Bank> banks = bankJdbcRepository.findAll();

        for (Bank bank:banks) {
            System.out.printf("Type: %-5s | Value: %-5d \n", bank.getType(), bank.getValue());
        }
        return amount;
    }
}
