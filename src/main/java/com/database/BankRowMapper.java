package com.database;

import com.model.Bank;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by khwanchanok on 4/6/2018 AD.
 */
@Component
public class BankRowMapper implements RowMapper{

    @Override
    public Bank mapRow(ResultSet resultSet, int row) throws SQLException {
        String bankType = resultSet.getString("type");
        int bankValue = resultSet.getInt("value");
        int amount = resultSet.getInt("amount");

        Bank bank = new Bank(bankType, bankValue);
        bank.setAmount(amount);

        return bank;
    }
}
