package com.ziky.bank.rest;

import com.ziky.bank.domain.Person;
import com.ziky.bank.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ziky on 10.12.2014.
 */
@Controller
public class EmployeeRestController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/rest/employees", method = RequestMethod.GET)
    public @ResponseBody List<Person> getAllEmployees() {
        return personService.getAllEmployees();
    }

    @RequestMapping(value = "/rest/employee/{id}", method = RequestMethod.GET)
    public @ResponseBody Person getEmployee(@PathVariable("id") String id) {
        return personService.getPersonDetails(id);
    }

    @RequestMapping(value = "/rest/employee/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@PathVariable("id") String id,@RequestBody Person person) {
        personService.updatePerson(person);
    }

    @RequestMapping(value = "/rest/employee/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") String id) {
        personService.deletePerson(personService.getPersonDetails(id));
    }

    @RequestMapping(value = "/rest/employee/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Person employee) {
        personService.createPerson(employee);
    }

    @RequestMapping(value = "/rest/persons", method = RequestMethod.GET)
    public @ResponseBody List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping(value = "/rest/personsnoaccount", method = RequestMethod.GET)
    public @ResponseBody List<Person> getAllPersonsWithoutAccount() {
        return personService.getPersonsWithoutAccount();
    }
}
