package com.example.lab4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class MainView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        LoginForm loginForm = new LoginForm();
        RegisterForm registerForm = new RegisterForm();
        primaryStage.setTitle("Main View");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            loginForm.openLoginForm();
        });
        gridPane.add(loginButton, 0, 0);

        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            registerForm.openRegisterForm();
        });
        gridPane.add(registerButton, 1, 0);

        Scene scene = new Scene(gridPane, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }





}



