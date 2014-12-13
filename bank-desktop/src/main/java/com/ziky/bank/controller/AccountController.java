/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziky.bank.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.ziky.bank.app.Main;
import com.ziky.bank.domain.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * FXML Controller class
 *
 * @author Ziky
 */
public class AccountController implements Initializable {

    @FXML
    private TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, Integer> numberColumn;
    @FXML
    private TableColumn<Account, LocalDate> creationTimeColumn;
    @FXML
    private TableColumn<Account, Float> balanceColumn;
    @FXML
    private TableColumn<Account, Float> overdraftColumn;
    @FXML
    private TableColumn<Account, Float> dailyLimitColumn;
    @FXML
    private TableColumn<Account, Float> monthlyLimitColumn;
    @FXML
    private TableColumn<Account, String> typeColumn;
    @FXML
    private TableColumn<Account, String> currencyColumn;
    @FXML
    private TableColumn<Account, String> ownerColumn;
    private final ObservableList<Account> accountData = FXCollections.observableArrayList();
    private RestTemplate restTemplate;
    private Stage stage;
    private boolean wasClosed;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        creationTimeColumn.setCellValueFactory(new PropertyValueFactory<>("creationTime"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        overdraftColumn.setCellValueFactory(new PropertyValueFactory<>("overDraft"));
        dailyLimitColumn.setCellValueFactory(new PropertyValueFactory<>("dailyLimit"));
        monthlyLimitColumn.setCellValueFactory(new PropertyValueFactory<>("monthlyLimit"));
        typeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTypeId().getName()));
        currencyColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCurrencyCode().getName()));
        ownerColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOwner().getFirstName() + " " + c.getValue().getOwner().getLastName()));

        restTemplate = new RestTemplate();
        ResponseEntity<Account[]> result = restTemplate.exchange("http://localhost:8080/rest/accounts", HttpMethod.GET, null, Account[].class);
        List<Account> list = Arrays.asList(result.getBody());

        accountData.addAll(list.stream().collect(Collectors.toList()));

        accountTable.setItems(accountData);
    }    
    
    @FXML
    public void handleNewAccount() {
        Account account = showNewAccountDialog();
        if(!wasClosed) {
            if (checkAccount(account)) {
                accountData.add(account);
                restTemplate.postForObject("http://localhost:8080/rest/account/create", account, Account.class);
                showSuccessDialog("Account created");
            } else {
                showErrorDialog("Invalid data input");
            }
        }
    }
    
    @FXML
    public void handleNewTransaction() {
        Tx transaction = showNewTransactionDialog();
        if(!wasClosed) {
            if (checkTransaction(transaction)) {
                restTemplate.postForObject("http://localhost:8080/rest/transaction/create", transaction, Tx.class);
                //Account account = restTemplate.getForObject("http://localhost:8080/rest/account/" +
                        //transaction.getAccountIdFrom().getAccountNumber(), Account.class);
                Account account = accountTable.getSelectionModel().getSelectedItem();

                if(transaction.getTypeId().getDescription().equals("deposit")) {
                    account.setBalance(account.getBalance() + transaction.getAmount());
                } else {
                    account.setBalance(account.getBalance() - transaction.getAmount());
                }
                restTemplate.put("http://localhost:8080/rest/account/{id}", transaction.getAccountIdFrom(),
                        transaction.getAccountIdFrom().getAccountNumber());
                refreshAccountTable();
                showSuccessDialog("Transaction created");
            } else {
                showErrorDialog("Invalid data input");
            }
        }
    }
    
    private Tx showNewTransactionDialog() {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("TransactionNew.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Transaction");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
            TransactionNewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            Account acc = accountTable.getSelectionModel().getSelectedItem();
            controller.setSelectedAccount(acc);
            //controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            this.wasClosed = controller.getClosed();
            //return controller.isOkClicked();
            return controller.getTransaction();
            //return null;
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
            return null;
        }
    }
    
    private Account showNewAccountDialog() {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("AccountEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Account");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
            AccountEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccNumber(getAccountNumber());
            //controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            this.wasClosed = controller.getClosed();
            //return controller.isOkClicked();
            return controller.getAccount();
            //return null;

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
            return null;
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private int getAccountNumber() {
        int max = 100000;

        for(int i = 0; i < accountData.size(); i++) {
            int value = accountData.get(i).getAccountNumber();
            if(value > max) {
                max = value;
            }
        }

        return max + 1;
    }

    private boolean checkAccount(Account account) {
        if(account == null)
            return false;
        if(account.getAccountNumber() == null)
            return false;
        if(account.getTypeId() == null)
            return false;
        if(account.getOwner() == null)
            return false;
        if(account.getCurrencyCode() == null)
            return false;
        if(account.getOverDraft() == null)
            return false;
        if(account.getBalance() == null)
            return false;
        if(account.getCreationTime() == null)
            return false;
        if(account.getDailyLimit() == null)
            return false;
        if(account.getMonthlyLimit() == null)
            return false;
        return true;
    }

    private boolean checkTransaction(Tx transaction) {
        if(transaction == null)
            return false;
        if(transaction.getTypeId() == null)
            return false;
        if(transaction.getCurrencyCode() == null)
            return false;
        if(transaction.getAccountIdFrom() == null)
            return false;
        if(transaction.getCreationDate() == null)
            return false;
        if(transaction.getAmount() == null)
            return false;
        return true;
    }

    private void showErrorDialog(String msg) {
        Dialogs.create()
                .owner(stage)
                .title("Error")
                .message(msg)
                .showError();
    }

    private void showSuccessDialog(String msg) {
        Dialogs.create()
                .owner(stage)
                .title("Success")
                .message(msg)
                .showInformation();
    }

    private void refreshAccountTable() {
        int selectedIndex = accountTable.getSelectionModel().getSelectedIndex();
        accountTable.setItems(null);
        accountTable.layout();
        accountTable.setItems(accountData);

        accountTable.getSelectionModel().select(selectedIndex);
    }
}
