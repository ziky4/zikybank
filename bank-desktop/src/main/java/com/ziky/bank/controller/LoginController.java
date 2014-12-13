package com.ziky.bank.controller;

import com.ziky.bank.domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    @FXML
    private Label errorLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private Stage primaryStage;
    private RestTemplate restTemplate;

    @FXML
    public void login(ActionEvent event) throws IOException {
        Person person = restTemplate.getForObject("http://localhost:8080/rest/employee/" + username.getText(), Person.class);
        boolean notNull = person != null;

        if(notNull && (person.getRoleName().getName().equals("admin") || person.getRoleName().getName().equals("employee"))
                && password.getText().equals(person.getPassword())) {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(LoginController.class.getClassLoader().getResource("Menu.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu");
            stage.setScene(new Scene(parent));

            MenuController controller = loader.getController();
            controller.setStage(stage);

            stage.show();
        }
        else {
            Dialogs.create()
                    .owner(primaryStage)
                    .title("Error")
                    .message("Invalid username or password")
                    .showError();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restTemplate = new RestTemplate();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
