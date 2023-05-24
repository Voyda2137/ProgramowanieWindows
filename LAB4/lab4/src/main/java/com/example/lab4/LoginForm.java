package com.example.lab4;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class LoginForm {
    private Stage loginStage;

    public void openLoginForm() {
        AtomicReference<Integer> counter = new AtomicReference<>(1);
        if (loginStage == null) {
            loginStage = new Stage();
            loginStage.setTitle("Login Form");

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setVgap(10);
            gridPane.setHgap(10);

            Label loginLabel = new Label("Login:");
            TextField loginTextField = new TextField();
            gridPane.add(loginLabel, 0, 0);
            gridPane.add(loginTextField, 1, 0);

            Label passwordLabel = new Label("Hasło:");
            PasswordField passwordField = new PasswordField();
            gridPane.add(passwordLabel, 0, 1);
            gridPane.add(passwordField, 1, 1);

            TextField visiblePasswordField = new TextField();
            visiblePasswordField.setVisible(false);
            gridPane.add(visiblePasswordField, 1, 1);

            CheckBox showPasswordCheckbox = new CheckBox("Pokaż Hasło");
            showPasswordCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    visiblePasswordField.setText(passwordField.getText());
                    passwordField.setVisible(false);
                    visiblePasswordField.setVisible(true);
                } else {
                    passwordField.setText(visiblePasswordField.getText());
                    passwordField.setVisible(true);
                    visiblePasswordField.setVisible(false);
                }
            });
            gridPane.add(showPasswordCheckbox, 1, 2);

            Button loginButton = new Button("Login");
            gridPane.add(loginButton, 0, 3);

            Scene scene = new Scene(gridPane, 300, 200);
            loginButton.setOnAction(e -> {
                String loginText = loginTextField.getText();
                String passwordText = passwordField.getText();
                if (loginText.isEmpty() || passwordText.isEmpty()) {
                    showAlertLogin("Error", "Należy podać Login i Hasło");
                } else {
                    Database db = new Database();
                    UserView userView = new UserView();
                    AdminView adminView = new AdminView();
                    if(db.authenticateUser(loginText, passwordText) == 1) {
                        adminView.openAdminView();
                    }
                    else if(db.authenticateUser(loginText, passwordText) == 0){
                        userView.openUserView(loginText);
                    }
                    else {
                        if(counter.get() == 3){
                            loginButton.setDisable(true);
                        }
                        else{
                            counter.getAndSet(counter.get() + 1);
                        }
                    }
                }
            });
            loginStage.setScene(scene);
            loginStage.setOnCloseRequest(event -> loginStage = null);
        }

        loginStage.show();
    }

    public void showAlertLogin(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
