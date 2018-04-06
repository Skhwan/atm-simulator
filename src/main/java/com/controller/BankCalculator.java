package com.controller;

import com.database.BankDao;
import com.util.ResponseWrapper;
import com.util.Constant;
import com.util.BankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */

@Component
public class BankCalculator {

    @Autowired
    BankDao bankDao;

    private final int[] bankValues = {1000, 500, 100, 50, 20};

    public ResponseWrapper calculateBank(int amount){
        String responseCode;
        String responseDesc;
        String responseStatus;
        ResponseWrapper responseWrapper = new ResponseWrapper();
        try {
            int[] bankAmounts = bankDao.getBankAmount();

            BankValidator.validateAmount(amount);
            BankValidator.validateRemainingBalance(amount, bankAmounts, bankValues);

            List<int[]> bankList = findBanks(amount, new int[bankAmounts.length], bankAmounts, 0);
            int[] selectedBankList = BankValidator.validateRemainingNote(bankAmounts, bankList);
            int[] updatedBankList = subtractBankAmt(bankAmounts, selectedBankList);
            bankDao.updateBalanceAmt(updatedBankList, bankValues);
            responseWrapper.setResponseBody(selectedBankList, bankValues);
            responseCode = Constant.SUCCESS_CODE;
            responseDesc = Constant.SUCCESS;
            responseStatus = Constant.SUCCESS;
        } catch (Exception e){
            responseCode = Constant.FAIL_CODE;
            responseDesc = e.getMessage();
            responseStatus = Constant.FAIL;
        }

        responseWrapper.setResponseCode(responseCode);
        responseWrapper.setResponseDesc(responseDesc);
        responseWrapper.setResponseStatus(responseStatus);
        return responseWrapper;
    }

    private List<int[]> findBanks(int amount, int[] currentBankAmt, int[] balanceBankAmt, int position){
        List<int[]> bankCombination = new ArrayList<>();
        int totalAmt = calCurrentTotalAmt(currentBankAmt);
        if (totalAmt < amount) {
            for (int i = position; i < currentBankAmt.length; i++) {
                if (balanceBankAmt[i] > currentBankAmt[i]) {
                    int newCurrentBankAmt[] = currentBankAmt.clone();
                    newCurrentBankAmt[i]++;
                    List<int[]> resultList = findBanks(amount, newCurrentBankAmt, balanceBankAmt, i);
                    if (resultList!=null) {
                        bankCombination.addAll(resultList);
                    }
                }
            }
        } else if (totalAmt == amount){
            bankCombination.add(currentBankAmt);
        }
        return bankCombination;
    }

    private int[] subtractBankAmt(int[] balanceBanks, int[] banks){
        for (int i = 0; i < banks.length; i++){
            balanceBanks[i] -= banks[i];
        }
        return balanceBanks;
    }

    private int calCurrentTotalAmt(int[] bankAmounts){
        int totalAmt = 0;
        for (int i = 0; i< bankAmounts.length; i++){
            totalAmt += bankAmounts[i]*bankValues[i];
        }
        return totalAmt;
    }
}
