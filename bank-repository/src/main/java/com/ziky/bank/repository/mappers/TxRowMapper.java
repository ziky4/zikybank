package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.*;
import org.springframework.jdbc.core.RowMapper;

import java.security.acl.Owner;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class TxRowMapper implements RowMapper<Tx> {
    @Override
    public Tx mapRow(ResultSet resultSet, int i) throws SQLException {
        Tx transaction = new Tx();
        transaction.setTxId(resultSet.getLong(1));
        transaction.setAmount(resultSet.getFloat(2));
        transaction.setCreationDate(resultSet.getDate(3));

        Account accountFrom = new Account();
        accountFrom.setAccountNumber(resultSet.getInt(4));
        transaction.setAccountIdFrom(accountFrom);

        Account accountTo = new Account();
        accountTo.setAccountNumber(resultSet.getInt(5));
        transaction.setAccountIdTo(accountTo);

        Bank bank = new Bank();
        bank.setBankCode(resultSet.getInt(6));
        bank.setName(resultSet.getString(7));
        transaction.setBankCode(bank);

        Currency currency = new Currency();
        currency.setCode(resultSet.getString(8));
        currency.setName(resultSet.getString(9));
        transaction.setCurrencyCode(currency);

        TxType txType = new TxType();
        txType.setTypeId(resultSet.getLong(10));
        txType.setDescription(resultSet.getString(11));
        transaction.setTypeId(txType);

        return transaction;
    }
}
