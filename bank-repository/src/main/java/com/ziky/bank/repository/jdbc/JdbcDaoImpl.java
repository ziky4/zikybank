package com.ziky.bank.repository.jdbc;

import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.Dao;
import com.ziky.bank.domain.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Ziky on 5.12.2014.
 */
@Repository
public abstract class JdbcDaoImpl<T extends Id<V>, V> implements Dao<T> {
    private DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;
    //protected Registry<T> registry = Registry.getInstance();

    @Override
    public void create(T t) {
        jdbcTemplate.update(getCreateSql(), getArgs(CrudType.CREATE, t));
        if(t.getId() != null) {
            addToRegistry(t.getId(), t);
        }
    }

    @Override
    public void delete(T t) {
        jdbcTemplate.update(getDeleteSql(), getArgs(CrudType.DELETE, t));
        deleteFromRegistry(t.getId());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(getDeleteAllSql());
        clearRegistry();
    }

    @Override
    public T find(Object id) {
        T result = getFromRegistry(id);//registry.getFromRegistry(id);
        if(result != null) {
            return result;
        }

        List<T> rows = jdbcTemplate.query(getFindSql(), new Object[]{id}, getRowMapper());
        if(rows.size() == 0) {
            return null;
        } else {
            T entity = rows.get(0);
            addToRegistry(entity.getId(), entity);
            return entity;
        }
    }

    @Override
    public void update(T t) {
        T ent = getFromRegistry(t.getId());
        addToRegistry(t.getId(), t);
        jdbcTemplate.update(getUpdateSql(), getArgs(CrudType.UPDATE, t));
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    protected abstract String getCreateSql();
    protected abstract Object[] getArgs(CrudType type, T entity);
    protected abstract String getDeleteSql();
    protected abstract String getDeleteAllSql();
    protected abstract String getFindSql();
    protected abstract String getUpdateSql();
    protected abstract RowMapper<T> getRowMapper();
    protected abstract void addToRegistry(Object id, T t);
    protected abstract T getFromRegistry(Object id);
    protected abstract void deleteFromRegistry(Object id);
    protected abstract void clearRegistry();
}
