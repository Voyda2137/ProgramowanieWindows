package com.example.lab4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

public class RegisterController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button submit;
    @FXML
    private TextArea name, surname, email, login;
    @FXML
    private TextField pwdV1, pwdV2;
    @FXML
    private PasswordField pwd1, pwd2;
    @FXML
    private CheckBox showPwd;
    private StackPane pane1, pane2;

    public RegisterController() {
    }

    @FXML
    protected void onSubmit() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void ShowPassword() {
        showPwd.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                StackPane parent = (StackPane) pwd1.getParent();
                ObservableList<Node> children = parent.getChildren();

                if (newVal) {
                    children.setAll(pwdV1, pwdV2, name, surname, email, login);
                } else {
                    children.setAll(pwd1, pwd2, pwdV1, pwdV2, name, surname, email, login);
                }
            }
        });
    }


}