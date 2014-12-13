package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.TxType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class TxTypeRowMapper implements RowMapper<TxType> {
    @Override
    public TxType mapRow(ResultSet resultSet, int i) throws SQLException {
        TxType txType = new TxType();
        txType.setTypeId(resultSet.getLong(1));
        txType.setDescription(resultSet.getString(2));

        return txType;
    }
}
