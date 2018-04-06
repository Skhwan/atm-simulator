package com.util;

import com.exception.InsufficientBalanceException;
import com.exception.InsufficientNoteException;
import com.exception.InvalidAmountException;

import java.util.List;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
public class BankValidator {

    public static boolean validateAmount(int amount) throws InvalidAmountException {
        if (amount < 20){
            throw new InvalidAmountException("Amount less than min amount");
        }
        return true;
    }

    public static boolean validateRemainingBalance(int amount, int[] bankAmount, int[] bankValues) throws InsufficientBalanceException {
        int totalAmt = 0;
        for(int i = 0; i < bankAmount.length; i++){
            totalAmt += bankValues[i]*bankAmount[i];
        }
        if (totalAmt < amount){
            throw new InsufficientBalanceException("Remaining balance less than dispense amount");
        }
        return true;
    }

    public static int[] validateRemainingNote(int[] bankLeft, List<int[]> totalBankNeeded) throws InsufficientNoteException {
        for (int[] bankNeeded:totalBankNeeded) {
            int[] resultBankSet = validateRemainingNote(bankLeft, bankNeeded);
            if (resultBankSet != null) {
                return resultBankSet;
            }
        }
        throw new InsufficientNoteException("Insufficient note number. Try dispensing a different amount.");
    }

    private static int[] validateRemainingNote(int[] bankLeft, int[] bankNeeded){
        for (int i = 0; i < bankLeft.length; i++) {
            if(bankLeft[i] < bankNeeded[i] && bankNeeded[i] > 0){
                return null;
            }
        }
        return bankNeeded;
    }
}
