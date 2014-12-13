package com.ziky.bank.controller;

import com.ziky.bank.app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Created by Ziky on 29.10.2014.
 */
public class MenuController implements Initializable {

    @FXML
    private BorderPane menuPane;
    private Stage stage;

    @FXML
    public void manageEmployees(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("Employee.fxml"));
        AnchorPane overview = (AnchorPane) loader.load();
        menuPane.setCenter(overview);
        EmployeeController controller = loader.getController();
        controller.setStage(stage);
        //controller.setMainApp();
    }
    
    @FXML
    public void manageAccounts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("Account.fxml"));
        AnchorPane overview = (AnchorPane) loader.load();
        menuPane.setCenter(overview);
        AccountController controller = loader.getController();
        controller.setStage(stage);
        //controller.setMainApp();
    }
    
    @FXML
    public void manageTransactions(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("Transaction.fxml"));
        AnchorPane overview = (AnchorPane) loader.load();
        menuPane.setCenter(overview);
    }
    
    @FXML
    public void closeApp() {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
