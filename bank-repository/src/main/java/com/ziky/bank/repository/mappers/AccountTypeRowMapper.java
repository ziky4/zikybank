package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.AccountType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class AccountTypeRowMapper implements RowMapper<AccountType> {
    @Override
    public AccountType mapRow(ResultSet resultSet, int i) throws SQLException {
        AccountType accountType = new AccountType();
        accountType.setTypeId(resultSet.getLong(1));
        accountType.setDebitInterestRate(resultSet.getFloat(2));
        accountType.setInterestRate(resultSet.getFloat(3));
        accountType.setMonthlyFee(resultSet.getFloat(4));
        accountType.setName(resultSet.getString(5));

        return accountType;
    }
}
