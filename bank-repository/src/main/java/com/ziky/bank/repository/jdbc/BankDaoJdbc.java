package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.Bank;
import com.ziky.bank.repository.BankDao;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.mappers.BankRowMapper;
import com.ziky.bank.repository.registry.BankRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 3.12.2014.
 */

@Repository
public class BankDaoJdbc extends JdbcDaoImpl<Bank, Integer> implements BankDao {
    private BankRegistry registry = BankRegistry.getInstance();

    private static final String SQL_SELECT_ALL = "SELECT bankcode, name FROM bank";
    private static final String SQL_SELECT_BY_CODE = "SELECT bankcode, name FROM bank WHERE bankcode = ?";
    private static final String SQL_INSERT = "INSERT INTO bank (bankcode, name) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE bank SET name=? WHERE bankcode=?";
    private static final String SQL_DELETE = "DELETE FROM bank WHERE bankcode=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM bank";

    @Override
    public List<Bank> findAllBanks() {
        List<Bank> banks = jdbcTemplate.query(SQL_SELECT_ALL, new BankRowMapper());
        List<Bank> result = new ArrayList<Bank>();

        for(Bank bank : banks) {
            if(getFromRegistry(bank.getId()) == null) {
                addToRegistry(bank.getId(), bank);
                result.add(bank);
            } else {
                result.add(getFromRegistry(bank.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, Bank bank) {
        Object[] args = new Object[]{};

        switch (type) {
            case CREATE:
                args = new Object[]{bank.getBankCode(), bank.getName()};
                break;
            case UPDATE:
                args = new Object[]{bank.getName(), bank.getBankCode()};
                break;
            case DELETE:
                args = new Object[]{bank.getBankCode()};
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
    protected RowMapper<Bank> getRowMapper() {
        return new BankRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, Bank bank) {
        registry.addToRegistry((Integer) id, bank);
    }

    @Override
    protected Bank getFromRegistry(Object id) {
        return registry.getFromRegistry((Integer) id);
    }

    @Override
    protected void deleteFromRegistry(Object id) {
        registry.deleteFromRegistry((Integer) id);
    }

    @Override
    protected void clearRegistry() {
        registry.clearRegistry();
    }
}
