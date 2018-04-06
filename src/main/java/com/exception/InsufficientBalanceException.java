package com.exception;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message){
        super(message);
    }
}
