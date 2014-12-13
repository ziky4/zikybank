/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziky.bank.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.ziky.bank.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * FXML Controller class
 *
 * @author Ziky
 */
public class AccountEditController implements Initializable {
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField balance;
    @FXML
    private TextField overDraft;
    @FXML
    private TextField dailyLimit;
    @FXML
    private TextField monthlyLimit;
    @FXML 
    private ComboBox<AccountType> accountType;
    private ObservableList<AccountType> accountTypes = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Currency> currency;
    private ObservableList<Currency> currencies = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Person> owner;
    private ObservableList<Person> persons = FXCollections.observableArrayList();
    private Stage dialogStage;
    private Account account;
    private RestTemplate restTemplate;
    private boolean closed;
    
    public AccountEditController() {
        restTemplate = new RestTemplate();

        ResponseEntity<Currency[]> result = restTemplate.exchange("http://localhost:8080/rest/currencies", HttpMethod.GET, null, Currency[].class);
        List<Currency> currenciesList = Arrays.asList(result.getBody());
        ResponseEntity<AccountType[]> res = restTemplate.exchange("http://localhost:8080/rest/accounttypes", HttpMethod.GET, null, AccountType[].class);
        List<AccountType> accountTypesList = Arrays.asList(res.getBody());
        ResponseEntity<Person[]> resultPersons = restTemplate.exchange("http://localhost:8080/rest/personsnoaccount", HttpMethod.GET, null, Person[].class);
        List<Person> accountTypePersons = Arrays.asList(resultPersons.getBody());

        currencies.addAll(currenciesList.stream().collect(Collectors.toList()));
        accountTypes.addAll(accountTypesList.stream().collect(Collectors.toList()));
        persons.addAll(accountTypePersons.stream().collect(Collectors.toList()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.account = null;
        this.accountNumber.setDisable(true);
        this.closed = true;

        currency.setItems(currencies);
        owner.setItems(persons);
        accountType.setItems(accountTypes);
        
        currency.setCellFactory((comboBox) -> {
            return new ListCell<Currency>() {
                @Override
                protected void updateItem(Currency item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                    setText(null);
                    } else {
                        setText(item.getCode() + " (" + item.getName() + ")");
                    }
                }
            };
        });
        
        currency.setConverter(new StringConverter<Currency>() {
            @Override
            public String toString(Currency person) {
                if (person == null) {
                    return null;
                } else {
                    return person.getCode() + " (" + person.getName() + ")";
                }
            }

            @Override
            public Currency fromString(String personString) {
                return null; // No conversion fromString needed.
            }
        });
        
        owner.setCellFactory((comboBox) -> {
            return new ListCell<Person>() {
                @Override
                protected void updateItem(Person item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getFirstName() + " " + item.getLastName());
                    }
                }
            };
        });


        owner.setConverter(new StringConverter<Person>() {
            @Override
            public String toString(Person person) {
                if (person == null) {
                    return null;
                } else {
                    return person.getFirstName() + " " + person.getLastName();
                }
            }

            @Override
            public Person fromString(String personString) {
                return null; // No conversion fromString needed.
            }
        });
        
        accountType.setCellFactory((comboBox) -> {
            return new ListCell<AccountType>() {
                @Override
                protected void updateItem(AccountType item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        });


        accountType.setConverter(new StringConverter<AccountType>() {
            @Override
            public String toString(AccountType person) {
                if (person == null) {
                    return null;
                } else {
                    return person.getName();
                }
            }

            @Override
            public AccountType fromString(String personString) {
                return null; // No conversion fromString needed.
            }
        });
        
        currency.getSelectionModel().select(currencies.get(0));
        if(persons.size() > 0) {
            owner.getSelectionModel().select(persons.get(0));
        }
        accountType.getSelectionModel().select(accountTypes.get(0));
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccNumber(Integer accNumber) {
        accountNumber.setText(accNumber + "");
    }

    public boolean getClosed() {
        return this.closed;
    }
    
    @FXML
    public void HandleAddAccount() {
        account = new Account();

        try {
            account.setAccountNumber(Integer.parseInt(accountNumber.getText()));
            account.setBalance(Float.parseFloat(balance.getText()));
            account.setCreationTime(new Date());
            account.setDailyLimit(Float.parseFloat(dailyLimit.getText()));
            account.setMonthlyLimit(Float.parseFloat(monthlyLimit.getText()));
            account.setOverDraft(Float.parseFloat(overDraft.getText()));
            account.setOwner(owner.getSelectionModel().getSelectedItem());
            account.setTypeId(accountType.getSelectionModel().getSelectedItem());
            account.setCurrencyCode(currency.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            account.setAccountNumber(null);
            account.setBalance(null);
            account.setCreationTime(null);
            account.setDailyLimit(null);
            account.setMonthlyLimit(null);
            account.setOverDraft(null);
            account.setOwner(null);
            account.setTypeId(null);
            account.setCurrencyCode(null);
        }
        closed = false;

        dialogStage.close();
    }
    
    @FXML
    public void HandleCancel() {
        closed = true;
        dialogStage.close();
    }
}
