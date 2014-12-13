package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setName(resultSet.getString(1));
        role.setDescription(resultSet.getString(2));

        return role;
    }
}
