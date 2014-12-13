package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.*;
import com.ziky.bank.repository.AccountDao;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.mappers.AccountRowMapper;
import com.ziky.bank.repository.registry.AccountRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 1.12.2014.
 */

@Repository
public class AccountDaoJdbc extends JdbcDaoImpl<Account,Integer> implements AccountDao {
    private AccountRegistry registry = AccountRegistry.getInstance();

    private static final String SQL_SELECT_BY_OWNER = "SELECT a.accountnumber, a.balance, a.creationtime, a.dailylimit," +
            " a.monthlylimit, a.overdraft, c.code, c.name, p.login, p.address, p.dateofbirth, p.email, p.firstname,"+
            " p.idcardno, p.lastname, p.password, p.phone, p.name," +
            " at.typeid, at.debitinterestrate, at.interestrate, at.monthlyfee, at.name" +
            " FROM account a" +
            " JOIN currency c ON a.code = c.code" +
            " JOIN person p ON a.owner = p.login" +
            " JOIN accounttype at ON a.typeid = at.typeid" +
            " WHERE p.login=?";
    private static final String SQL_SELECT_BY_ID = "SELECT a.accountnumber, a.balance, a.creationtime, a.dailylimit," +
            " a.monthlylimit, a.overdraft, c.code, c.name, p.login, p.address, p.dateofbirth, p.email, p.firstname,"+
            " p.idcardno, p.lastname, p.password, p.phone, p.name," +
            " at.typeid, at.debitinterestrate, at.interestrate, at.monthlyfee, at.name" +
            " FROM account a" +
            " JOIN currency c ON a.code = c.code" +
            " JOIN person p ON a.owner = p.login" +
            " JOIN accounttype at ON a.typeid = at.typeid" +
            " WHERE a.accountnumber=?";
    private static final String SQL_SELECT_ALL = "SELECT a.accountnumber, a.balance, a.creationtime, a.dailylimit," +
            " a.monthlylimit, a.overdraft, c.code, c.name as cname, p.*, at.typeid, at.debitinterestrate," +
            " at.interestrate, at.monthlyfee, at.name as atname" +
            " FROM account a" +
            " JOIN currency c ON a.code = c.code" +
            " JOIN person p ON a.owner = p.login" +
            " JOIN accounttype at ON a.typeid = at.typeid";
    private static final String SQL_INSERT = "INSERT INTO account(accountnumber, balance, creationtime," +
            " dailylimit, monthlylimit, overdraft, code, owner, typeid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE account SET balance=?," +
            " dailylimit=?, monthlylimit=?, overdraft=?, code=?, owner=?, typeid=? WHERE accountnumber=?";
    private static final String SQL_DELETE = "DELETE FROM account WHERE accountnumber=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM account";

    @Override
    public Account findAccountByOwner(String ownerLogin) {
        List<Account> accounts = jdbcTemplate.query(SQL_SELECT_BY_OWNER, new Object[]{ownerLogin}, new AccountRowMapper());
        if(accounts.size() == 0) {
            return null;
        } else {
            Account account = accounts.get(0);

            if(getFromRegistry(account.getId()) == null) {
                addToRegistry(account.getId(), account);
            } else {
                account = getFromRegistry(account.getId());
            }

            return account;
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = jdbcTemplate.query(SQL_SELECT_ALL, new AccountRowMapper());
        List<Account> result = new ArrayList<Account>();

        for(Account account : accounts) {
            if(getFromRegistry(account.getId()) == null) {
                addToRegistry(account.getId(), account);
                result.add(account);
            } else {
                result.add(getFromRegistry(account.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, Account account) {
        Object[] args = new Object[]{};

        switch (type) {
            case CREATE:
                args =new Object[]{account.getAccountNumber(), account.getBalance(), account.getCreationTime(),
                    account.getDailyLimit(), account.getMonthlyLimit(), account.getOverDraft(),
                    account.getCurrencyCode().getCode(), account.getOwner().getLogin(), account.getTypeId().getTypeId()};
                break;
            case UPDATE:
                args = new Object[]{account.getBalance(), account.getDailyLimit(),
                    account.getMonthlyLimit(), account.getOverDraft(), account.getCurrencyCode().getCode(),
                    account.getOwner().getLogin(), account.getTypeId().getTypeId(), account.getAccountNumber()};
                break;
            case DELETE:
                args = new Object[]{account.getAccountNumber()};
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
    protected RowMapper<Account> getRowMapper() {
        return new AccountRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, Account account) {
        registry.addToRegistry((Integer) id, account);
    }

    @Override
    protected Account getFromRegistry(Object id) {
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
