package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.TxType;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.TxTypeDao;
import com.ziky.bank.repository.mappers.TxTypeRowMapper;
import com.ziky.bank.repository.registry.TxTypeRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 3.12.2014.
 */

@Repository
public class TxTypeDaoJdbc extends JdbcDaoImpl<TxType, Long> implements TxTypeDao {
    private TxTypeRegistry registry = TxTypeRegistry.getInstance();

    private static final String SQL_SELECT_ALL = "SELECT typeid, description FROM txtype";
    private static final String SQL_SELECT_BY_ID = "SELECT typeid, description FROM txtype WHERE typeid = ?";
    private static final String SQL_SELECT_BY_DESCRIPTION = "SELECT typeid, description FROM txtype WHERE description = ?";
    private static final String SQL_INSERT = "INSERT INTO txtype(description) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE txtype SET description=? WHERE typeid=?";
    private static final String SQL_DELETE = "DELETE FROM txtype WHERE typeid=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM txtype";

    @Override
    public TxType findTransactionTypeByDescription(String description) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_DESCRIPTION, new Object[]{description}, new TxTypeRowMapper());
    }

    @Override
    public List<TxType> findAllTransactionTypes() {
        List<TxType> txTypes = jdbcTemplate.query(SQL_SELECT_ALL, new TxTypeRowMapper());
        List<TxType> result = new ArrayList<TxType>();

        for(TxType txType : txTypes) {
            if(getFromRegistry(txType.getId()) == null) {
                addToRegistry(txType.getId(), txType);
                result.add(txType);
            } else {
                result.add(getFromRegistry(txType.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, TxType transactionType) {
        Object[] args = new Object[]{};

        switch (type) {
            case CREATE:
                args = new Object[]{transactionType.getDescription()};
                break;
            case UPDATE:
                args = new Object[]{transactionType.getDescription(), transactionType.getTypeId()};
                break;
            case DELETE:
                args = new Object[]{transactionType.getTypeId()};
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
    protected RowMapper<TxType> getRowMapper() {
        return new TxTypeRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, TxType type) {
       registry.addToRegistry((Long) id, type);
    }

    @Override
    protected TxType getFromRegistry(Object id) {
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
