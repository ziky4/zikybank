package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.Tx;
import com.ziky.bank.domain.TxType;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.TxDao;
import com.ziky.bank.repository.mappers.TxRowMapper;
import com.ziky.bank.repository.registry.TxRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ziky on 3.12.2014.
 */

@Repository
public class TxDaoJdbc extends JdbcDaoImpl<Tx, Long> implements TxDao {
    private TxRegistry registry = TxRegistry.getInstance();

    private static final String SQL_SELECT_BY_ID = "SELECT t.txid, t.amount, t.creationdate, t.accountidfrom," +
            " t.accountidto, b.*, c.*, tt.*" +
            " FROM tx t" +
            " JOIN bank b ON t.bankcode = b.bankcode" +
            " JOIN currency c ON t.currencycode = c.code" +
            " JOIN txtype tt ON t.typeid = tt.typeid" +
            " WHERE t.txid=?";
    private static final String SQL_SELECT_ALL = "SELECT t.txid, t.amount, t.creationdate, t.accountidfrom," +
            " t.accountidto, b.*, c.*, tt.*" +
            " FROM tx t" +
            " JOIN bank b ON t.bankcode = b.bankcode" +
            " JOIN currency c ON t.currencycode = c.code" +
            " JOIN txtype tt ON t.typeid = tt.typeid";
    private static final String SQL_SELECT_BY_TYPE = "SELECT t.txid, t.amount, t.creationdate, t.accountidfrom," +
            " t.accountidto, b.*, c.*, tt.*" +
            " FROM tx t" +
            " JOIN bank b ON t.bankcode = b.bankcode" +
            " JOIN currency c ON t.currencycode = c.code" +
            " JOIN txtype tt ON t.typeid = tt.typeid" +
            " WHERE t.accountidfrom=? AND t.typeid=? AND(t.creationdate BETWEEN ? AND ?)";
    private static final String SQL_SELECT_BY_DATE = "SELECT t.txid, t.amount, t.creationdate, t.accountidfrom," +
            " t.accountidto, b.*, c.*, tt.*" +
            " FROM tx t" +
            " JOIN bank b ON t.bankcode = b.bankcode" +
            " JOIN currency c ON t.currencycode = c.code" +
            " JOIN txtype tt ON t.typeid = tt.typeid" +
            " WHERE t.accountidfrom=? AND t.creationdate=?";
    private static final String SQL_SELECT_BETWEEN_DATES = "SELECT t.txid, t.amount, t.creationdate, t.accountidfrom," +
            " t.accountidto, b.*, c.*, tt.*" +
            " FROM tx t" +
            " JOIN bank b ON t.bankcode = b.bankcode" +
            " JOIN currency c ON t.currencycode = c.code" +
            " JOIN txtype tt ON t.typeid = tt.typeid" +
            " WHERE (t.accountidfrom=? OR t.accountidto=?) AND(t.creationdate BETWEEN ? AND ?)";
    private static final String SQL_INSERT = "INSERT INTO tx(amount, creationdate, accountidfrom," +
            " accountidto, bankcode, currencycode, typeid) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_NO_ACCTO = "INSERT INTO tx(amount, creationdate, accountidfrom," +
            " bankcode, currencycode, typeid) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE tx SET amount=?, creationdate=?, accountidfrom=?, " +
            "accountidto=?, bankcode=?, currencycode=?, typeid=? WHERE txid=?";
    private static final String SQL_DELETE = "DELETE FROM tx WHERE txid=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM tx";

    @Override
    public List<Tx> findTransactionsByDay(Integer accountNumber, Date date) {
        Object[] args = new Object[]{accountNumber, date};
        List<Tx> transactions = jdbcTemplate.query(SQL_SELECT_BY_DATE, args, new TxRowMapper());
        List<Tx> result = new ArrayList<Tx>();

        for(Tx transaction : transactions) {
            if(getFromRegistry(transaction.getId()) == null) {
                addToRegistry(transaction.getId(), transaction);
                result.add(transaction);
            } else {
                result.add(getFromRegistry(transaction.getId()));
            }
        }
        return result;
    }

    @Override
    public List<Tx> findTransactionsBetweenDates(Integer accountNumber, Date startDate, Date endDate) {
        Object[] args = new Object[]{accountNumber, accountNumber, startDate, endDate};
        List<Tx> transactions = jdbcTemplate.query(SQL_SELECT_BETWEEN_DATES, args, new TxRowMapper());
        List<Tx> result = new ArrayList<Tx>();

        for(Tx transaction : transactions) {
            if(getFromRegistry(transaction.getId()) == null) {
                addToRegistry(transaction.getId(), transaction);
                result.add(transaction);
            } else {
                result.add(getFromRegistry(transaction.getId()));
            }
        }
        return result;
    }

    @Override
    public List<Tx> findTransactionsByType(Integer accountNumber, TxType type, Date startDate, Date endDate) {
        Object[] args = new Object[]{accountNumber, type.getTypeId(), startDate, endDate};
        List<Tx> transactions = jdbcTemplate.query(SQL_SELECT_BY_TYPE, args, new TxRowMapper());
        List<Tx> result = new ArrayList<Tx>();

        for(Tx transaction : transactions) {
            if(getFromRegistry(transaction.getId()) == null) {
                addToRegistry(transaction.getId(), transaction);
                result.add(transaction);
            } else {
                result.add(getFromRegistry(transaction.getId()));
            }
        }
        return result;
    }

    @Override
    public List<Tx> findAllTransactions() {
        List<Tx> transactions = jdbcTemplate.query(SQL_SELECT_ALL, new TxRowMapper());
        List<Tx> result = new ArrayList<Tx>();

        for(Tx transaction : transactions) {
            if(getFromRegistry(transaction.getId()) == null) {
                addToRegistry(transaction.getId(), transaction);
                result.add(transaction);
            } else {
                result.add(getFromRegistry(transaction.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, Tx transaction) {
        Object[] args = new Object[]{};
        Integer accountTo = null;

        if(transaction.getAccountIdTo() != null) {
            accountTo = transaction.getAccountIdTo().getAccountNumber();
        }

        switch (type) {
            case CREATE:
                args = new Object[]{transaction.getAmount(), transaction.getCreationDate(),
                    transaction.getAccountIdFrom().getAccountNumber(), accountTo,
                    transaction.getBankCode().getBankCode(), transaction.getCurrencyCode().getCode(),
                    transaction.getTypeId().getTypeId()};
                break;
            case UPDATE:
                args = new Object[]{transaction.getAmount(), transaction.getCreationDate(),
                    transaction.getAccountIdFrom().getAccountNumber(), accountTo,
                    transaction.getBankCode().getBankCode(), transaction.getCurrencyCode().getCode(),
                    transaction.getTypeId().getTypeId(), transaction.getTxId()};
                break;
            case DELETE:
                args = new Object[]{transaction.getTxId()};
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
    protected RowMapper<Tx> getRowMapper() {
        return new TxRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, Tx tx) {
        registry.addToRegistry((Long) id, tx);
    }

    @Override
    protected Tx getFromRegistry(Object id) {
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
