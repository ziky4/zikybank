package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class BankRowMapper implements RowMapper<Bank> {
    @Override
    public Bank mapRow(ResultSet resultSet, int i) throws SQLException {
        Bank bank = new Bank();
        bank.setBankCode(resultSet.getInt(1));
        bank.setName(resultSet.getString(2));

        return bank;
    }
}
