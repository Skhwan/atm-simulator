package com.controller;

import com.database.DatabaseManager;
import com.model.ResponseWrapper;
import com.util.Constant;
import com.util.BankValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */

@Controller
public class BankCalculator {

    private final Logger logger = LoggerFactory.getLogger(BankCalculator.class);

    @Autowired
    DatabaseManager databaseManager;

    public ResponseEntity<ResponseWrapper> calculateBank(int amount){
        String responseCode;
        String responseDesc;
        String responseStatus;
        ResponseWrapper responseWrapper = new ResponseWrapper();
        try {
            int[] bankAmounts = databaseManager.getBankAmount();
            int[] bankValues = databaseManager.getBankValues();

            BankValidator.validateAmount(amount);
            BankValidator.validateRemainingBalance(amount, bankAmounts, bankValues);

            List<int[]> bankList = findBanks(amount, new int[bankAmounts.length], bankAmounts, bankValues, 0);
            int[] selectedBankList = BankValidator.validateRemainingNote(bankAmounts, bankList);
            int[] updatedBankList = subtractBankAmt(bankAmounts, selectedBankList);
            databaseManager.updateBalanceAmt(updatedBankList, bankValues);
            responseWrapper.setResponseBody(selectedBankList, bankValues);
            responseCode = Constant.SUCCESS_CODE;
            responseDesc = Constant.SUCCESS;
            responseStatus = Constant.SUCCESS;
        } catch (Exception e){
            logger.error("Got Exception while processing : {}", e.getMessage());
            responseCode = Constant.FAIL_CODE;
            responseDesc = e.getMessage();
            responseStatus = Constant.FAIL;
        }

        responseWrapper.setResponseCode(responseCode);
        responseWrapper.setResponseDesc(responseDesc);
        responseWrapper.setResponseStatus(responseStatus);

        ResponseEntity<ResponseWrapper> response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
        logger.info("Response {}", response);
        return response;
    }

    public List<int[]> findBanks(int amount, int[] currentBankAmt, int[] balanceBankAmt, int[] bankValues, int position){
        List<int[]> bankCombination = new ArrayList<>();
        int totalAmt = calCurrentTotalAmt(currentBankAmt, bankValues);
        if (totalAmt < amount) {
            for (int i = position; i < currentBankAmt.length; i++) {
                if (balanceBankAmt[i] > currentBankAmt[i]) {
                    int newCurrentBankAmt[] = currentBankAmt.clone();
                    newCurrentBankAmt[i]++;
                    List<int[]> resultList = findBanks(amount, newCurrentBankAmt, balanceBankAmt, bankValues, i);
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

    public int[] subtractBankAmt(int[] balanceBanks, int[] banks){
        for (int i = 0; i < banks.length; i++){
            balanceBanks[i] -= banks[i];
        }
        return balanceBanks;
    }

    public int calCurrentTotalAmt(int[] bankAmounts, int[] bankValues){
        int totalAmt = 0;
        for (int i = 0; i< bankAmounts.length; i++){
            totalAmt += bankAmounts[i]*bankValues[i];
        }
        return totalAmt;
    }
}
