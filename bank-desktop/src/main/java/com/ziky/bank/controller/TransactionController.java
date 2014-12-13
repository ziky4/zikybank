/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziky.bank.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.ziky.bank.domain.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * FXML Controller class
 *
 * @author Ziky
 */
public class TransactionController implements Initializable {
    @FXML
    private TableView<Tx> txTable;
    @FXML
    private TableColumn<Tx, Long> txId;
    @FXML
    private TableColumn<Tx, LocalDateTime> creationDate;
    @FXML
    private TableColumn<Tx, Float> amount;
    @FXML
    private TableColumn<Tx, String> currency;
    @FXML
    private TableColumn<Tx, String> bank;
    @FXML
    private TableColumn<Tx, String> accountFrom;
    @FXML
    private TableColumn<Tx, String> accountTo;
    @FXML
    private TableColumn<Tx, String> type;
    private final ObservableList<Tx> txData = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txId.setCellValueFactory(new PropertyValueFactory<>("txId"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        currency.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCurrencyCode().getName()));
        bank.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBankCode().getName()));
        accountFrom.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAccountIdFrom().getAccountNumber().toString()));
        accountTo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAccountIdTo().getAccountNumber().toString()));
        type.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTypeId().getDescription()));

        accountTo.setCellFactory(column -> {
            return new TableCell<Tx, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null && item.equals("0")) {
                        setText("");
                    } else {
                        setText(item);
                    }
                }
            };
        });

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tx[]> result = restTemplate.exchange("http://localhost:8080/rest/transactions", HttpMethod.GET, null, Tx[].class);
        List<Tx> list = Arrays.asList(result.getBody());

        txData.addAll(list.stream().collect(Collectors.toList()));

        txTable.setItems(txData);
    }    
    
}
