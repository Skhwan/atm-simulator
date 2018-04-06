package com.service;

import com.controller.BankCalculator;
import com.exception.InsufficientBalanceException;
import com.exception.InsufficientNoteException;
import com.exception.InvalidAmountException;
import com.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseWrapper> deposit(@PathVariable int amount) throws InsufficientNoteException, InvalidAmountException, InsufficientBalanceException {

        ResponseWrapper response;
        response = bankCalculator.calculateBank(amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
