package com.ziky.bank.app;

import java.io.IOException;

import com.ziky.bank.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane loginLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ziky Bank");
        initLoginLayout();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void initLoginLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("Login.fxml"));
            loader.setLocation(Main.class.getClassLoader().getResource("Login.fxml"));
            loginLayout = (AnchorPane) loader.load();
                        
            // Show the scene containing the login layout.
            Scene scene = new Scene(loginLayout, 250, 180);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setPrimaryStage(this.primaryStage);
            //controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
	    }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public AnchorPane getLoginLayout() {
        return loginLayout;
    }

    public void setLoginLayout(AnchorPane loginLayout) {
        this.loginLayout = loginLayout;
    }
    
    
}
