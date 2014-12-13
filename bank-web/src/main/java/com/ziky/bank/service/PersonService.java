package com.ziky.bank.service;

import com.ziky.bank.domain.Person;

import java.util.List;

/**
 * Created by Ziky on 6.10.2014.
 */
public interface PersonService {
    public void createPerson(Person person);
    public Person getPersonDetails(String login);
    public void deleteAll();
    public List<Person> getAllEmployees();
    public List<Person> getAllPersons();
    public List<Person> getPersonsWithoutAccount();
    public void deletePerson(Person person);
    public void updatePerson(Person person);
}
