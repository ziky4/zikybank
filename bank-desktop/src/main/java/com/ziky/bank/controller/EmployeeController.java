package com.ziky.bank.controller;

import com.ziky.bank.app.Main;
import com.ziky.bank.domain.Person;
import com.ziky.bank.domain.Role;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Ziky on 29.10.2014.
 */
public class EmployeeController implements Initializable {
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelFirstName;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelCardNumber;
    @FXML
    private Label labelDateOfBirth;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelAddress;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    private final ObservableList<Person> personData = FXCollections.observableArrayList();
    private RestTemplate restTemplate;
    private Stage stage;
    private boolean wasClosed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        restTemplate = new RestTemplate();
        ResponseEntity<Person[]> result = restTemplate.exchange("http://localhost:8080/rest/employees", HttpMethod.GET, null, Person[].class);
        List<Person> list = Arrays.asList(result.getBody());

        personData.addAll(list.stream().collect(Collectors.toList()));

        personTable.setItems(personData);
        showEmployeeDetail(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable,
                                Person oldValue, Person newValue) {
                showEmployeeDetail(newValue);
            }
        });
    }

    private void showEmployeeDetail(Person person) {
        if(person == null) {
            labelLogin.setText("");
            labelFirstName.setText("");
            labelLastName.setText("");
            labelCardNumber.setText("");
            labelDateOfBirth.setText("");
            labelEmail.setText("");
            labelPhone.setText("");
            labelAddress.setText("");
        }
        else {
            labelLogin.setText(person.getLogin());
            labelFirstName.setText(person.getFirstName());
            labelLastName.setText(person.getLastName());
            labelCardNumber.setText(person.getIdCardNo() + "");
            labelDateOfBirth.setText(person.getDateOfBirth() + "");
            labelEmail.setText(person.getEmail());
            labelPhone.setText(person.getPhone());
            labelAddress.setText(person.getAddress());
        }
    }

    @FXML
    public void handleNewEmployee() {
        Person person = showPersonEditDialog();
        if(!wasClosed) {
            if (checkEmployee(person)) {
                Role role = restTemplate.getForObject("http://localhost:8080/rest/role/employee", Role.class);
                person.setRoleName(role);
                personData.add(person);
                restTemplate.postForObject("http://localhost:8080/rest/employee/create", person, Person.class);
                showSuccessDialog("Employee created");
            } else {
                showErrorDialog("Invalid data input");
            }
        }
    }

    @FXML
    public void handleEditEmployee() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        int index = personTable.getSelectionModel().getSelectedIndex();

        if (selectedPerson != null) {
            Person person = showDialog(selectedPerson);
            if(!wasClosed) {
                if (checkEmployee(person)) {
                    Role role = restTemplate.getForObject("http://localhost:8080/rest/role/employee", Role.class);
                    person.setRoleName(role);
                    restTemplate.put("http://localhost:8080/rest/employee/{id}", person, person.getLogin());
                    personData.set(index, person);
                    showSuccessDialog("Employee updated");
                } else {
                    showErrorDialog("Invalid data input");
                }
                refreshPersonTable();
            }
        }
    }

    @FXML
    public void handleDeleteEmployee() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Person person = personTable.getItems().get(selectedIndex);
            restTemplate.delete("http://localhost:8080/rest/employee/{id}", person.getLogin());
            personTable.getItems().remove(selectedIndex);
            showSuccessDialog("Employee deleted");
        }
    }

    private Person showDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("EmployeeEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
            EmployeeEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            this.wasClosed = controller.getClosed();
            //return controller.isOkClicked();
            return controller.getPerson();

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
            return null;
        }
    }

    private Person showPersonEditDialog() {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("EmployeeEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Person");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
            EmployeeEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLoginNumber(getLoginNumber());
            //controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            this.wasClosed = controller.getClosed();
            //return controller.isOkClicked();
            return controller.getPerson();

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
            return null;
        }
    }

    private void refreshPersonTable() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        personTable.setItems(null);
        personTable.layout();
        personTable.setItems(personData);

        personTable.getSelectionModel().select(selectedIndex);
    }

    private String getLoginNumber() {
        int max = 1000;

        ResponseEntity<Person[]> result = restTemplate.exchange("http://localhost:8080/rest/persons", HttpMethod.GET, null, Person[].class);
        List<Person> personsList = Arrays.asList(result.getBody());

        for(int i = 0; i < personsList.size(); i++) {
            int value = Integer.parseInt(personsList.get(i).getLogin());
            if(value > max) {
                max = value;
            }
        }

        return (max + 1) + "";
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

    private boolean checkEmployee(Person emp) {
        if(emp == null)
            return false;
        if(emp.getLogin() == null)
            return false;
        if(emp.getPhone() == null)
            return false;
        if(emp.getPassword() == null)
            return false;
        if(emp.getFirstName() == null)
            return false;
        if(emp.getLastName() == null)
            return false;
        if(emp.getIdCardNo() == null)
            return false;
        if(emp.getAddress() == null)
            return false;
        if(emp.getDateOfBirth() == null)
            return false;
        if(emp.getEmail() == null)
            return false;
        return true;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
