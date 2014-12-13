package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.Currency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class CurrencyRowMapper implements RowMapper<Currency> {
    @Override
    public Currency mapRow(ResultSet resultSet, int i) throws SQLException {
        Currency currency = new Currency();
        currency.setCode(resultSet.getString(1));
        currency.setName(resultSet.getString(2));

        return currency;
    }
}
