package com.ziky.bank.init;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ziky.bank.domain.*;
import com.ziky.bank.repository.json.CurrencyDaoJson;
import com.ziky.bank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ziky on 14.10.2014.
 */

@Component
public class Boot {

    @Autowired
    private TxTypeService txTypeService;
    @Autowired
    private TransactionService txService;
    @Autowired
    private BankService bankService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PersonService personService;
    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void init() throws ParseException {
        txService.deleteAll();
        accountService.deleteAll();
        personService.deleteAll();
        txTypeService.deleteAll();
        roleService.deleteAll();
        bankService.deleteAll();
        accountTypeService.deleteAll();
        currencyService.deleteAll();

        TxType typeTransfer = new TxType("transfer");
        TxType typeDeposit = new TxType("deposit");
        TxType typeWithdraw = new TxType("withdraw");

        txTypeService.createTxType(typeTransfer);
        txTypeService.createTxType(typeDeposit);
        txTypeService.createTxType(typeWithdraw);

        typeTransfer = txTypeService.getTransactionTypeByName("transfer");
        typeDeposit = txTypeService.getTransactionTypeByName("deposit");
        typeWithdraw = txTypeService.getTransactionTypeByName("withdraw");

        Bank zikyBank = new Bank(4444, "Ziky bank");
        Bank csobBank = new Bank(2277, "CSOB");
        Bank kbBank = new Bank(1441, "KB");
        Bank nbBank = new Bank(9966, "NB");

        bankService.createBank(zikyBank);
        bankService.createBank(csobBank);
        bankService.createBank(kbBank);
        bankService.createBank(nbBank);

        Currency currencyCz = new Currency("cz", "Kc");
        Currency currencyEu = new Currency("us", "$");
        Currency currencyEn = new Currency("en", "€");

        currencyService.createCurrency(currencyCz);
        currencyService.createCurrency(currencyEu);
        currencyService.createCurrency(currencyEn);

        AccountType accTypeSavings = new AccountType("savings", new Float(5.0), new Float(2.0), new Float(60.0));
        AccountType accTypePersonal = new AccountType("personal", new Float(5.0), new Float(2.0), new Float(60.0));
        AccountType accTypeStudent = new AccountType("student", new Float(5.0), new Float(2.0), new Float(60.0));

        accountTypeService.createAccountType(accTypeSavings);
        accountTypeService.createAccountType(accTypePersonal);
        accountTypeService.createAccountType(accTypeStudent);

        accTypeSavings = accountTypeService.getAccountByName("savings");
        accTypePersonal = accountTypeService.getAccountByName("personal");
        accTypeStudent = accountTypeService.getAccountByName("student");

        Role roleUser = new Role("user", "access for classic users");
        Role roleAdmin = new Role("admin", "special type for high position employer");
        Role roleEmployee = new Role("employee", "access employers");

        roleService.createRole(roleUser);
        roleService.createRole(roleEmployee);
        roleService.createRole(roleAdmin);

        Person personOne = new Person("1000", "pass", "Radek", "Zika", 6056878, new SimpleDateFormat("dd/MM/yyyy")
                .parse("04/05/1992"), "zikadad@email.com", "600599887", "Cajk 7 Havirov", roleUser);
        Person personTwo = new Person("1001", "pass", "Petr", "Vida", 4488779, new SimpleDateFormat("dd/MM/yyyy")
                .parse("02/01/1973"), "lololo@email.com", "702564893","Kala 4 Ostrava", roleUser);
        Person personThree = new Person("1002", "pass", "Josef", "Cajk", 1122778, new SimpleDateFormat("dd/MM/yyyy")
                .parse("11/11/1968"), "asdd@email.com", "774125663","Nova 1 Karvina", roleEmployee);
        Person personFour = new Person("1003", "pass", "Jiri", "Kulo", 6050201, new SimpleDateFormat("dd/MM/yyyy")
                .parse("10/26/1989"), "grrr@email.com", "666882564","Bila 6 Trinec", roleAdmin);
        Person personFive = new Person("1004", "pass", "Jana", "Hnusna", 6050201, new SimpleDateFormat("dd/MM/yyyy")
                .parse("10/26/1989"), "nonon@email.com", "666882564","Doma 7", roleUser);

        personService.createPerson(personOne);
        personService.createPerson(personTwo);
        personService.createPerson(personThree);
        personService.createPerson(personFour);
        personService.createPerson(personFive);

        Account accOne = new Account(101010, new Float(2000), new Float(5000), new Float(20000),
                new Float(30000), accTypePersonal, currencyCz, personOne);
        Account accTwo = new Account(101515, new Float(2450), new Float(3000), new Float(10000),
                new Float(15000), accTypeSavings, currencyEu, personTwo);

        accountService.createAccount(accOne);
        accountService.createAccount(accTwo);

        txService.createTransaction(new Tx(new Float(200), currencyCz, zikyBank, accOne, accTwo, typeTransfer));
        txService.createTransaction(new Tx(new Float(500), currencyCz, zikyBank, accOne, accTwo, typeTransfer));
        txService.createTransaction(new Tx(new Float(600), currencyCz, zikyBank, accOne, accTwo, typeTransfer));

        /*String lol = (System.getProperty("user.home") + File.separator + ".bank");
        CurrencyDaoJson tmp = new CurrencyDaoJson();

        tmp.deleteAll();

        tmp.create(currencyCz);
        tmp.create(currencyEu);
        tmp.create(currencyEn);*/
        /*List<Currency> hhh = tmp.findAllCurrencies();
        Currency currencyTmp = new Currency("vu", "ll");
        tmp.create(currencyTmp);
        hhh = tmp.findAllCurrencies();
        currencyTmp.setName("ku");
        hhh = tmp.findAllCurrencies();
        tmp.delete(currencyTmp);
        hhh = tmp.findAllCurrencies();
        tmp.deleteAll();
        hhh = tmp.findAllCurrencies();*/

        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/bank/rest/employee/1000", Person.class);*/
    }
}
