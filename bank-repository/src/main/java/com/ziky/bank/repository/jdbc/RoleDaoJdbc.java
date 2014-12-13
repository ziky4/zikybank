package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.Role;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.RoleDao;
import com.ziky.bank.repository.mappers.RoleRowMapper;
import com.ziky.bank.repository.registry.RoleRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 3.12.2014.
 */

@Repository
public class RoleDaoJdbc extends JdbcDaoImpl<Role, String> implements RoleDao {
    private RoleRegistry registry = RoleRegistry.getInstance();

    private static final String SQL_SELECT_ALL = "SELECT name, description FROM role";
    private static final String SQL_SELECT_BY_NAME = "SELECT name, description FROM role WHERE name = ?";
    private static final String SQL_INSERT = "INSERT INTO role(name, description) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE role SET description=? WHERE name=?";
    private static final String SQL_DELETE = "DELETE FROM role WHERE name=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM role";

    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = jdbcTemplate.query(SQL_SELECT_ALL, new RoleRowMapper());
        List<Role> result = new ArrayList<Role>();

        for(Role role : roles) {
            if(getFromRegistry(role.getId()) == null) {
                addToRegistry(role.getId(), role);
                result.add(role);
            } else {
                result.add(getFromRegistry(role.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, Role role) {
        Object[] args = new Object[]{};

        switch (type) {
            case CREATE:
                args = new Object[]{role.getName(), role.getDescription()};
                break;
            case UPDATE:
                args = new Object[]{role.getDescription(), role.getName()};
                break;
            case DELETE:
                args = new Object[]{role.getName()};
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
        return SQL_SELECT_BY_NAME;
    }

    @Override
    protected String getUpdateSql() {
        return SQL_UPDATE;
    }

    @Override
    protected RowMapper<Role> getRowMapper() {
        return new RoleRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, Role role) {
        registry.addToRegistry((String) id, role);
    }

    @Override
    protected Role getFromRegistry(Object id) {
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
