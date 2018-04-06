package com.util;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
public class BankMapper {

    public static String getBankType(int bankValue){
        String bankType;
        switch (bankValue){
            case 1000:
                bankType = BankEnum.THOUSAND.name();
                break;
            case 500:
                bankType = BankEnum.FIVE_HUNDRED.name();
                break;
            case 100:
                bankType = BankEnum.ONE_HUNDRED.name();
                break;
            case 50:
                bankType = BankEnum.FIFTY.name();
                break;
            default:
                bankType = BankEnum.TWENTY.name();
                break;
        }
        return bankType;
    }

}
