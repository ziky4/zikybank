package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.AccountType;
import com.ziky.bank.repository.AccountTypeDao;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.mappers.AccountTypeRowMapper;
import com.ziky.bank.repository.registry.AccountTypeRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 3.12.2014.
 */

@Repository
public class AccountTypeDaoJdbc extends JdbcDaoImpl<AccountType, Long> implements AccountTypeDao {
    private AccountTypeRegistry registry = AccountTypeRegistry.getInstance();

    private static final String SQL_SELECT_ALL = "SELECT typeid, debitinterestrate," +
            " interestrate, monthlyfee, name FROM accounttype";
    private static final String SQL_SELECT_BY_ID = "SELECT typeid, debitinterestrate," +
            " interestrate, monthlyfee, name FROM accounttype WHERE typeid=?";
    private static final String SQL_SELECT_BY_NAME = "SELECT typeid, debitinterestrate," +
            " interestrate, monthlyfee, name FROM accounttype WHERE name=?";
    private static final String SQL_INSERT = "INSERT INTO accounttype(debitinterestrate, interestrate," +
            " monthlyfee, name) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE accounttype SET debitinterestrate=?, interestrate=?," +
            " monthlyfee=?, name=? WHERE typeid=?";
    private static final String SQL_DELETE = "DELETE FROM accounttype WHERE typeid=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM accounttype";

    @Override
    public AccountType findAccountTypeByName(String name) {
        List<AccountType> accountTypes = jdbcTemplate.query(SQL_SELECT_BY_NAME, new Object[]{name}, new AccountTypeRowMapper());
        if(accountTypes.size() == 0) {
            return null;
        } else {
            AccountType accountType = accountTypes.get(0);

            if(getFromRegistry(accountType.getId()) == null) {
                addToRegistry(accountType.getId(), accountType);
            } else {
                accountType = getFromRegistry(accountType.getId());
            }

            return accountType;
        }
    }

    @Override
    public List<AccountType> findAllAccountTypes() {
        List<AccountType> accountTypes = jdbcTemplate.query(SQL_SELECT_ALL, new AccountTypeRowMapper());
        List<AccountType> result = new ArrayList<AccountType>();

        for(AccountType accountType : accountTypes) {
            if(getFromRegistry(accountType.getId()) == null) {
                addToRegistry(accountType.getId(), accountType);
                result.add(accountType);
            } else {
                result.add(getFromRegistry(accountType.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, AccountType accountType) {
        Object[] args = new Object[]{};

        switch (type) {
            case CREATE:
                args = new Object[]{accountType.getDebitInterestRate(), accountType.getInterestRate(),
                    accountType.getMonthlyFee(), accountType.getName()};
                break;
            case UPDATE:
                args = new Object[]{accountType.getDebitInterestRate(), accountType.getInterestRate(),
                    accountType.getMonthlyFee(), accountType.getName(), accountType.getTypeId()};
                break;
            case DELETE:
                args = new Object[]{accountType.getTypeId()};
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
        return SQL_SELECT_BY_ID;
    }

    @Override
    protected String getUpdateSql() {
        return SQL_UPDATE;
    }

    @Override
    protected RowMapper<AccountType> getRowMapper() {
        return new AccountTypeRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, AccountType accountType) {
        registry.addToRegistry((Long) id, accountType);
    }

    @Override
    protected AccountType getFromRegistry(Object id) {
        return registry.getFromRegistry((Long) id);
    }

    @Override
    protected void deleteFromRegistry(Object id) {
        registry.deleteFromRegistry((Long) id);
    }

    @Override
    protected void clearRegistry() {
        registry.clearRegistry();
    }
}
