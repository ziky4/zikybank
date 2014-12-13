package com.ziky.bank.service;

import com.ziky.bank.domain.Person;
import com.ziky.bank.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ziky on 14.10.2014.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public void createPerson(Person person) {
        personDao.create(person);
    }

    @Override
    public Person getPersonDetails(String login) {
        return personDao.find(login);
    }

    @Override
    public void deleteAll() {
        personDao.deleteAll();
    }

    @Override
    public List<Person> getAllEmployees() {
        return personDao.findAllEmployees();
    }

    @Override
    public List<Person> getAllPersons() {
        return personDao.findAllPersons();
    }

    @Override
    public List<Person> getPersonsWithoutAccount() {
        return personDao.findAllPersonsWithoutAccount();
    }

    @Override
    public void deletePerson(Person person) {
        personDao.delete(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDao.update(person);
    }
}
