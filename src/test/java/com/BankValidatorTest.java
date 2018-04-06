package com;

import com.exception.InsufficientBalanceException;
import com.exception.InsufficientNoteException;
import com.exception.InvalidAmountException;
import com.util.BankValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankValidatorTest {

    final int[] bankValues = {1000, 500, 100, 50, 20};

    @Test
    public void validateMinAmountSuccess() throws InvalidAmountException {
        boolean result = BankValidator.validateAmount(50);
        Assert.assertTrue(result);
    }

    @Test
    public void validateMinAmountFailWithInvalidAmountException() {
        String expectedErr = "Amount less than min amount";
        String errMsg = null;
        try {
            boolean result = BankValidator.validateAmount(10);
        } catch (InvalidAmountException e){
            errMsg = e.getMessage();
        }

        Assert.assertEquals(expectedErr, errMsg);
    }

    @Test
    public void validateRemainingBalanceSuccess() throws InsufficientBalanceException {
        int neededAmt = 180;
        int[] remainingBank = {5, 5, 5, 5, 5};

        boolean result = BankValidator.validateRemainingBalance(neededAmt, remainingBank, bankValues);
        Assert.assertTrue(result);
    }

    @Test
    public void validateRemainingBalanceFailWithInsufficientBalanceException() throws InsufficientBalanceException {
        int neededAmt = 180;
        int[] remainingBank = {0, 0, 0, 0, 5};

        String expectedErr = "Remaining balance less than dispensed amount";
        String errMsg = null;

        try {
            BankValidator.validateRemainingBalance(neededAmt, remainingBank, bankValues);
        } catch (InsufficientBalanceException e){
            errMsg = e.getMessage();
        }
        Assert.assertEquals(expectedErr, errMsg);
    }

    @Test
    public void validateRemainingNoteSuccess() throws InsufficientNoteException {
        int[] remainingBank = {5, 5, 5, 5, 5};
        List<int[]> bankNeeded = new ArrayList<>();
        int[] list1 = {6, 5, 1, 1, 0};
        int[] list2 = {2, 5, 1, 1, 0};
        bankNeeded.add(list1);
        bankNeeded.add(list2);

        int[] result = BankValidator.validateRemainingNote(remainingBank, bankNeeded);

        Assert.assertEquals(list2, result);
    }

    @Test
    public void validateRemainingNoteFailWithInsufficientNoteException() throws InsufficientNoteException {
        int[] remainingBank = {0, 0, 0, 0, 0};
        List<int[]> bankNeeded = new ArrayList<>();
        int[] list1 = {6, 5, 1, 1, 0};
        bankNeeded.add(list1);

        String expectedErr = "Insufficient note number. Try dispensing a different amount.";
        String errMsg = null;
        try {
            int[] result = BankValidator.validateRemainingNote(remainingBank, bankNeeded);
        } catch (InsufficientNoteException e){
            errMsg = e.getMessage();
        }

        Assert.assertEquals(expectedErr, errMsg);
    }

}
