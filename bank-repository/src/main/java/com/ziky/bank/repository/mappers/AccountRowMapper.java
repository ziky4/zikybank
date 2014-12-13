package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setAccountNumber(resultSet.getInt(1));
        account.setBalance(resultSet.getFloat(2));
        account.setCreationTime(resultSet.getTimestamp(3));
        account.setDailyLimit(resultSet.getFloat(4));
        account.setMonthlyLimit(resultSet.getFloat(5));
        account.setOverDraft(resultSet.getFloat(6));

        Currency currency = new Currency();
        currency.setCode(resultSet.getString(7));
        currency.setName(resultSet.getString(8));
        account.setCurrencyCode(currency);

        Person person = new Person();
        person.setLogin(resultSet.getString(9));
        person.setAddress(resultSet.getString(10));
        person.setDateOfBirth(resultSet.getDate(11));
        person.setEmail(resultSet.getString(12));
        person.setFirstName(resultSet.getString(13));
        person.setIdCardNo(resultSet.getInt(14));
        person.setLastName(resultSet.getString(15));
        person.setPassword(resultSet.getString(16));
        person.setPhone(resultSet.getString(17));
        Role role = new Role();
        role.setName(resultSet.getString(18));
        person.setRoleName(role);
        account.setOwner(person);

        AccountType accountType = new AccountType();
        accountType.setTypeId(resultSet.getLong(19));
        accountType.setDebitInterestRate(resultSet.getFloat(20));
        accountType.setInterestRate(resultSet.getFloat(21));
        accountType.setMonthlyFee(resultSet.getFloat(22));
        accountType.setName(resultSet.getString(23));
        account.setTypeId(accountType);

        return account;
    }
}
