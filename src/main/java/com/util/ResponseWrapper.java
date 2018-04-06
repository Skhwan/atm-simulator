package com.util;

import com.model.Bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
public class ResponseWrapper {

    private List<Bank> banks;
    private String responseCode;
    private String responseDesc;
    private String responseStatus;

    public ResponseWrapper(){
        this.banks = new ArrayList<>();
    }

    public void setResponseBody(int[] banks, int[] bankValues){
        for (int i = 0; i < banks.length; i++){
            if (banks[i] > 0) {
                Bank bank = new Bank(BankMapper.getBankType(bankValues[i]), bankValues[i]);
                bank.setAmount(banks[i]);
                this.banks.add(bank);
            }
        }
    }

    public void setResponseCode(String responseCode){
        this.responseCode = responseCode;
    }

    public void setResponseDesc(String responseDesc){
        this.responseDesc = responseDesc;
    }

    public void setResponseStatus(String responseStatus){
        this.responseStatus = responseStatus;
    }

    public List<Bank> getResponseBody(){
        return banks;
    }

    public String getResponseCode(){
        return responseCode;
    }

    public String getResponseDesc(){
        return responseDesc;
    }

    public String getResponseStatus(){
        return responseStatus;
    }
}
