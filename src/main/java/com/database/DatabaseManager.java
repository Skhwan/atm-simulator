package com.database;

import com.model.Bank;
import com.util.BankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
@Component
public class DatabaseManager {

    private final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    @Autowired
    BankJdbcRepository bankJdbcRepository;

    private int[] bankValues;

    public int[] getBankAmount(){
        List<Bank> banks = bankJdbcRepository.findAll();
        bankValues = new int[banks.size()];

        int bankAmount[] = new int[banks.size()];
        for (int i = 0; i< banks.size(); i++) {
            bankAmount[i] = banks.get(i).getAmount();
            bankValues[i] = banks.get(i).getValue();
        }
        logger.info("Got data from database : {}", banks);
        return bankAmount;
    }

    public int[] getBankValues(){
        return bankValues;
    }

    public void updateBalanceAmt(int[] updatedBankAmt, int[] bankValues){
        for (int i = 0; i< updatedBankAmt.length; i++) {
            int bankValue = bankValues[i];
            String bankType = BankMapper.getBankType(bankValue);
            bankJdbcRepository.update(new Bank(bankType, bankValue), updatedBankAmt[i]);
        }
    }
}
