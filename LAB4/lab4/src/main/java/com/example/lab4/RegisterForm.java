package com.example.lab4;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterForm {
    private Stage registerStage;

    public void openRegisterForm() {
        if (registerStage == null) {
            registerStage = new Stage();
            registerStage.setTitle("Register Form");

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setVgap(10);
            gridPane.setHgap(10);

            Label nameLabel = new Label("Imię:");
            TextField nameTextField = new TextField();
            gridPane.add(nameLabel, 0, 0);
            gridPane.add(nameTextField, 1, 0);

            Label surnameLabel = new Label("Nazwisko:");
            TextField surnameTextField = new TextField();
            gridPane.add(surnameLabel, 0, 1);
            gridPane.add(surnameTextField, 1, 1);

            Label loginLabel = new Label("Login:");
            TextField loginTextField = new TextField();
            gridPane.add(loginLabel, 0, 2);
            gridPane.add(loginTextField, 1, 2);

            Label emailLabel = new Label("Email:");
            TextField emailTextField = new TextField();
            gridPane.add(emailLabel, 0, 3);
            gridPane.add(emailTextField, 1, 3);

            Label passwordLabel = new Label("Hasło:");
            PasswordField passwordField = new PasswordField();
            gridPane.add(passwordLabel, 0, 4);
            gridPane.add(passwordField, 1, 4);

            TextField visiblePasswordField = new TextField();
            visiblePasswordField.setVisible(false);
            gridPane.add(visiblePasswordField, 1, 4);

            Label repeatPasswordLabel = new Label("Powtórz Hasło:");
            PasswordField repeatPasswordField = new PasswordField();
            gridPane.add(repeatPasswordLabel, 0, 5);
            gridPane.add(repeatPasswordField, 1, 5);

            TextField visibleRepeatPasswordField = new TextField();
            visibleRepeatPasswordField.setVisible(false);
            gridPane.add(visibleRepeatPasswordField, 1, 5);

            CheckBox showPasswordCheckbox = new CheckBox("Pokaż Hasło");
            showPasswordCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    visiblePasswordField.setText(passwordField.getText());
                    passwordField.setVisible(false);
                    visiblePasswordField.setVisible(true);

                    visibleRepeatPasswordField.setText(repeatPasswordField.getText());
                    repeatPasswordField.setVisible(false);
                    visibleRepeatPasswordField.setVisible(true);
                } else {
                    passwordField.setText(visiblePasswordField.getText());
                    passwordField.setVisible(true);
                    visiblePasswordField.setVisible(false);

                    repeatPasswordField.setText(visibleRepeatPasswordField.getText());
                    repeatPasswordField.setVisible(true);
                    visibleRepeatPasswordField.setVisible(false);
                }
            });
            HBox checkboxContainer = new HBox(showPasswordCheckbox);
            checkboxContainer.setAlignment(Pos.CENTER_LEFT);
            gridPane.add(checkboxContainer, 1, 6);

            Button registerButton = new Button("Zarejestruj");
            registerButton.setOnAction(event -> {
                if (validateFields(nameTextField, surnameTextField, loginTextField, emailTextField, passwordField, repeatPasswordField)) {
                    Database database = new Database();
                    database.insertUser(
                            nameTextField.getText(),
                            surnameTextField.getText(),
                            loginTextField.getText(),
                            emailTextField.getText(),
                            passwordField.getText()
                    );
                    registerStage.close();
                    registerStage = null;
                }
            });
            gridPane.add(registerButton, 0, 7, 2, 1);

            Scene scene = new Scene(gridPane, 300, 300);
            registerStage.setScene(scene);
        }

        registerStage.show();
    }
    private boolean validateFields(TextField nameField, TextField surnameField, TextField loginField,
                                   TextField emailField, PasswordField passwordField, PasswordField repeatPasswordField) {
        if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || loginField.getText().isEmpty()
                || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty()) {
            showErrorAlert("Wypełnij wszystkie pola");
            return false;
        }

        if (!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            showErrorAlert("Nieprawidłowy email");
            return false;
        }

        if (!passwordField.getText().equals(repeatPasswordField.getText())) {
            showErrorAlert("Hasła są różne");
            return false;
        }

        return true;
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
