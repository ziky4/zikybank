/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziky.bank.controller;

import java.net.URL;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import com.ziky.bank.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class TransactionNewController implements Initializable {
    @FXML
    private TextField account;
    @FXML
    private TextField amount;
    @FXML
    private TextField currency;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<TxType> txType;
    private ObservableList<TxType> types = FXCollections.observableArrayList();
    private Stage dialogStage;
    private Tx transaction;
    private Account selectedAccount;
    private RestTemplate restTemplate;
    private boolean closed;

    public TransactionNewController() {
        restTemplate = new RestTemplate();

        ResponseEntity<TxType[]> result = restTemplate.exchange("http://localhost:8080/rest/txtypes", HttpMethod.GET, null, TxType[].class);
        List<TxType> list = Arrays.asList(result.getBody());

        types.addAll(list.stream().collect(Collectors.toList()));
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Tx getTransaction() {
        return transaction;
    }

    public void setTransaction(Tx transaction) {
        this.transaction = transaction;
    }

    public Account getSelectedAccount() {
        return this.selectedAccount;
    }

    public boolean getClosed() {
        return this.closed;
    }

    public void setSelectedAccount(Account acc) {
        this.closed = true;

        this.selectedAccount = acc;
        account.setText(selectedAccount.getAccountNumber().toString());
        account.setDisable(true);
        currency.setText(selectedAccount.getCurrencyCode().getName());
        currency.setDisable(true);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.transaction = null;
        this.selectedAccount = null;
        this.closed = true;
        
        date.setValue(LocalDate.now());
        txType.setItems(types);
        //accountTo.setItems(accounts);
        
        txType.setCellFactory((comboBox) -> {
            return new ListCell<TxType>() {
                @Override
                protected void updateItem(TxType item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                    setText(null);
                    } else {
                        setText(item.getDescription());
                    }
                }
            };
        });
        
        txType.setConverter(new StringConverter<TxType>() {
            @Override
            public String toString(TxType person) {
                if (person == null) {
                    return null;
                } else {
                    return person.getDescription();
                }
            }

            @Override
            public TxType fromString(String personString) {
                return null; // No conversion fromString needed.
            }
        });
        
        txType.getSelectionModel().select(types.get(0));
    }    
    
    @FXML
    public void handleAddTransaction() {
        transaction = new Tx();

        Bank bank = restTemplate.getForObject("http://localhost:8080/rest/bank/4444", Bank.class);

        Instant instant = date.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        transaction.setCreationDate(Date.from(instant));
        try {
            transaction.setAmount(Float.parseFloat(amount.getText()));
        } catch (Exception e) {
            transaction.setAmount(null);
        }
        transaction.setAccountIdFrom(selectedAccount);
        transaction.setAccountIdTo(null);
        transaction.setBankCode(bank);
        transaction.setCurrencyCode(selectedAccount.getCurrencyCode());
        transaction.setTypeId(txType.getValue());
        closed = false;

        dialogStage.close();
    }
    
    @FXML
    public void handleCancel() {
        closed = true;
        dialogStage.close();
    }
}
