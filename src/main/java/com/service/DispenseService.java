package com.service;

import com.controller.BankCalculator;
import com.exception.InsufficientBalanceException;
import com.exception.InsufficientNoteException;
import com.exception.InvalidAmountException;
import com.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */

@RestController
public class DispenseService {

    @Autowired
    BankCalculator bankCalculator;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @GetMapping("/dispense")
    public ResponseEntity<ResponseWrapper> deposit(
            @RequestParam("amount") int amount)
            throws InsufficientNoteException, InvalidAmountException, InsufficientBalanceException {
        return bankCalculator.calculateBank(amount);
    }

}
