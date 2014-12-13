package com.ziky.bank.repository.mappers;

import com.ziky.bank.domain.Person;
import com.ziky.bank.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ziky on 3.12.2014.
 */
public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setLogin(resultSet.getString(1));
        person.setAddress(resultSet.getString(2));
        person.setDateOfBirth(resultSet.getDate(3));
        person.setEmail(resultSet.getString(4));
        person.setFirstName(resultSet.getString(5));
        person.setIdCardNo(resultSet.getInt(6));
        person.setLastName(resultSet.getString(7));
        person.setPassword(resultSet.getString(8));
        person.setPhone(resultSet.getString(9));

        Role role = new Role();
        role.setName(resultSet.getString(10));
        role.setDescription(resultSet.getString(11));
        person.setRoleName(role);

        return person;
    }
}
