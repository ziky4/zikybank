package com.ziky.bank.controller;

import com.ziky.bank.domain.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Ziky on 29.10.2014.
 */
public class EmployeeEditController implements Initializable {

    @FXML
    private DatePicker dateField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField firstNameFiled;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private PasswordField passwordField;
    private Stage dialogStage;
    private Person person;
    private boolean closed;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Person getPerson() {
        return person;
    }

    public void setLoginNumber(String login) {
        loginField.setText(login);
    }

    public void setPerson(Person person) {
        this.person = person;
        closed = true;

        dateField.setValue(person.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        firstNameFiled.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        loginField.setText(person.getLogin());
        cardNumberField.setText(Integer.toString(person.getIdCardNo()));
        emailField.setText(person.getEmail());
        phoneField.setText(person.getPhone());
        addressField.setText(person.getAddress());
        passwordField.setText(person.getPassword());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.person = new Person();
        closed = true;
        loginField.setDisable(true);
    }

    @FXML
    public void addPerson() throws ParseException {
        this.person = new Person();
        SimpleDateFormat sdf =  new SimpleDateFormat("MM, dd, yyyy");

        person.setLogin(loginField.getText());
        person.setFirstName(firstNameFiled.getText());
        person.setLastName(lastNameField.getText());
        try {
            person.setIdCardNo(Integer.parseInt(cardNumberField.getText()));
        } catch (Exception e) {
            person.setIdCardNo(null);
        }
        Instant instant = dateField.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        person.setDateOfBirth(Date.from(instant));
        person.setEmail(emailField.getText());
        person.setPhone(phoneField.getText());
        person.setAddress(addressField.getText());
        person.setPassword(passwordField.getText());

        closed = false;

        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        closed = true;
        dialogStage.close();
    }

    public boolean getClosed() {
        return this.closed;
    }
}
