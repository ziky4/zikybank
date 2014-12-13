package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.Currency;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.CurrencyDao;
import com.ziky.bank.repository.mappers.CurrencyRowMapper;
import com.ziky.bank.repository.registry.CurrencyRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 3.12.2014.
 */

@Repository
public class CurrencyDaoJdbc extends JdbcDaoImpl<Currency, String> implements CurrencyDao{
    private CurrencyRegistry registry = CurrencyRegistry.getInstance();

    private static final String SQL_SELECT_ALL = "SELECT code, name FROM currency";
    private static final String SQL_SELECT_BY_CODE = "SELECT code, name FROM currency WHERE code = ?";
    private static final String SQL_INSERT = "INSERT INTO currency (code, name) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE currency SET name=? WHERE code=?";
    private static final String SQL_DELETE = "DELETE FROM currency WHERE code=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM currency";

    @Override
    public List<Currency> findAllCurrencies() {
        List<Currency> currencies = jdbcTemplate.query(SQL_SELECT_ALL, new CurrencyRowMapper());
        List<Currency> result = new ArrayList<Currency>();

        for(Currency currency : currencies) {
            if(getFromRegistry(currency.getId()) == null) {
                addToRegistry(currency.getId(), currency);
                result.add(currency);
            } else {
                result.add(getFromRegistry(currency.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, Currency currency) {
        Object[] args = new Object[]{};

        switch (type) {
            case CREATE:
                args = new Object[]{currency.getCode(), currency.getName()};
                break;
            case UPDATE:
                args = new Object[]{currency.getName(), currency.getCode()};
                break;
            case DELETE:
                args = new Object[]{currency.getCode()};
                break;
        }

        return args;
    }

    @Override
    protected String getDeleteSql() {
        return SQL_DELETE;
    }

    @Override
    protected String getDeleteAllSql() {
        return SQL_DELETE_ALL;
    }

    @Override
    protected String getFindSql() {
        return SQL_SELECT_BY_CODE;
    }

    @Override
    protected String getUpdateSql() {
        return SQL_UPDATE;
    }

    @Override
    protected RowMapper<Currency> getRowMapper() {
        return new CurrencyRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, Currency currency) {
        registry.addToRegistry((String) id, currency);
    }

    @Override
    protected Currency getFromRegistry(Object id) {
        return registry.getFromRegistry((String) id);
    }

    @Override
    protected void deleteFromRegistry(Object id) {
        registry.deleteFromRegistry((String) id);
    }

    @Override
    protected void clearRegistry() {
        registry.clearRegistry();
    }
}
