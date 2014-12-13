package com.ziky.bank.repository;

import com.ziky.bank.domain.Person;

import java.util.List;

/**
 * Created by Ziky on 3.10.2014.
 */
public interface PersonDao extends Dao<Person>{
    //public Person findPersonByLogin(String login);
    public List<Person> findAllPersons();
    public List<Person> findAllEmployees();
    public List<Person> findAllPersonsWithoutAccount();
    //public void savePerson(Person person);
    //public void deletePerson(Person person);
    //public void deleteAll();
    //public Person updatePerson(Person person);
}
