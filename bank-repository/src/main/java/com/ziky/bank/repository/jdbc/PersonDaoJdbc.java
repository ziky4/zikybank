package com.ziky.bank.repository.jdbc;

import com.ziky.bank.domain.Person;
import com.ziky.bank.repository.CrudType;
import com.ziky.bank.repository.PersonDao;
import com.ziky.bank.repository.mappers.PersonRowMapper;
import com.ziky.bank.repository.registry.PersonRegistry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziky on 3.12.2014.
 */

@Repository
public class PersonDaoJdbc extends JdbcDaoImpl<Person, String> implements PersonDao {
    private PersonRegistry registry = PersonRegistry.getInstance();

    private static final String SQL_SELECT_ALL = "SELECT p.login, p.address, p.dateofbirth, p.email, p.firstname," +
            " p.idcardno, p.lastname, p.password, p.phone, r.name, r.description" +
            " FROM person p JOIN role r ON p.name = r.name";
    private static final String SQL_SELECT_ALL_NO_ACC = "SELECT p.login, p.address, p.dateofbirth, p.email, p.firstname," +
            " p.idcardno, p.lastname, p.password, p.phone, r.name, r.description" +
            " FROM person p JOIN role r ON p.name = r.name" +
            " WHERE p.name = 'user' AND p.login NOT IN" +
            " (SELECT p.login FROM person p JOIN account a ON p.login = a.owner)";
    private static final String SQL_SELECT_ALL_EMP = "SELECT p.login, p.address, p.dateofbirth, p.email, p.firstname," +
            " p.idcardno, p.lastname, p.password, p.phone, r.name, r.description" +
            " FROM person p JOIN role r ON p.name = r.name" +
            " WHERE (p.name = 'admin' OR p.name = 'employee')";
    private static final String SQL_SELECT_BY_LOGIN = "SELECT p.login, p.address, p.dateofbirth, p.email, p.firstname," +
            " p.idcardno, p.lastname, p.password, p.phone, r.name, r.description" +
            " FROM person p JOIN role r ON p.name = r.name WHERE p.login=?";
    private static final String SQL_INSERT = "INSERT INTO person(login, address, dateofbirth," +
            " email, firstname, idcardno, lastname, password, phone, name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE person SET address=?, dateofbirth=?," +
            " email=?, firstname=?, idcardno=?, lastname=?, password=?, phone=?, name=? WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM person WHERE login=?";
    private static final String SQL_DELETE_ALL = "DELETE FROM person";

    @Override
    public List<Person> findAllPersons() {
        List<Person> persons = jdbcTemplate.query(SQL_SELECT_ALL, new PersonRowMapper());
        List<Person> result = new ArrayList<Person>();

        for(Person person : persons) {
            if(getFromRegistry(person.getId()) == null) {
                addToRegistry(person.getId(), person);
                result.add(person);
            } else {
                result.add(getFromRegistry(person.getId()));
            }
        }
        return result;
    }

    @Override
    public List<Person> findAllEmployees() {
        List<Person> persons = jdbcTemplate.query(SQL_SELECT_ALL_EMP, new PersonRowMapper());
        List<Person> result = new ArrayList<Person>();

        for(Person person : persons) {
            if(getFromRegistry(person.getId()) == null) {
                addToRegistry(person.getId(), person);
                result.add(person);
            } else {
                result.add(getFromRegistry(person.getId()));
            }
        }
        return result;
    }

    @Override
    public List<Person> findAllPersonsWithoutAccount() {
        List<Person> persons = jdbcTemplate.query(SQL_SELECT_ALL_NO_ACC, new PersonRowMapper());
        List<Person> result = new ArrayList<Person>();

        for(Person person : persons) {
            if(getFromRegistry(person.getId()) == null) {
                addToRegistry(person.getId(), person);
                result.add(person);
            } else {
                result.add(getFromRegistry(person.getId()));
            }
        }
        return result;
    }

    @Override
    protected String getCreateSql() {
        return SQL_INSERT;
    }

    @Override
    protected Object[] getArgs(CrudType type, Person person) {
        Object[] args = new Object[]{};

        switch (type) {
            case CREATE:
                args = new Object[]{person.getLogin(), person.getAddress(), person.getDateOfBirth(), person.getEmail(),
                    person.getFirstName(), person.getIdCardNo(), person.getLastName(), person.getPassword(),
                    person.getPhone(), person.getRoleName().getName()};
                break;
            case UPDATE:
                args = new Object[]{person.getAddress(), person.getDateOfBirth(), person.getEmail(),
                    person.getFirstName(), person.getIdCardNo(), person.getLastName(), person.getPassword(),
                    person.getPhone(), person.getRoleName().getName(), person.getLogin()};
                break;
            case DELETE:
                args = new Object[]{person.getLogin()};
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
        return SQL_SELECT_BY_LOGIN;
    }

    @Override
    protected String getUpdateSql() {
        return SQL_UPDATE;
    }

    @Override
    protected RowMapper<Person> getRowMapper() {
        return new PersonRowMapper();
    }

    @Override
    protected void addToRegistry(Object id, Person person) {
        registry.addToRegistry((String) id, person);
    }

    @Override
    protected Person getFromRegistry(Object id) {
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
