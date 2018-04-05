package com.service;

import com.controller.BankCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */

@RestController
public class DispenseService {

    @Autowired
    BankCalculator bankCalculator;

    @GetMapping("/deposit/{amount}")
    public int deposit(@PathVariable int amount){
        return bankCalculator.calculateBank(amount);
    }

}
