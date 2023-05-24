package com.example.lab4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminView {
    private Stage primaryStage;

    public AdminView() {
        primaryStage = new Stage();
        primaryStage.setTitle("Admin Panel");
        RegisterForm addUser = new RegisterForm();
        Database db = new Database();
        AddEventView events = new AddEventView();
        EditEventView editEventView = new EditEventView();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label userLabel = new Label("Zarządzanie Użytkownikami");
        gridPane.add(userLabel, 0, 0);

        ComboBox<String> usersBox = new ComboBox<>();
        usersBox.setItems(db.getUsers());
        gridPane.add(usersBox, 0, 1);

        Button addUserButton = new Button("Dodaj Użytkownika");
        gridPane.add(addUserButton, 0, 2);

        Button deleteUserButton = new Button("Usuń Użytkownika");
        gridPane.add(deleteUserButton, 0, 3);

        Button resetPasswordButton = new Button("Zresetuj Hasło");
        gridPane.add(resetPasswordButton, 0, 4);

        Label eventLabel = new Label("Zarządzanie Wydarzeniami");
        gridPane.add(eventLabel, 1, 0);

        ComboBox<String> eventsBox = new ComboBox<>();
        eventsBox.setItems(db.getEvents());
        gridPane.add(eventsBox, 1, 1);

        Button addEventButton = new Button("Dodaj Wydarzenie");
        gridPane.add(addEventButton, 1, 2);

        Button deleteEventButton = new Button("Usuń Wydarzenie");
        gridPane.add(deleteEventButton, 1, 3);

        Button modifyEventButton = new Button("Edytuj Wydarzenie");
        gridPane.add(modifyEventButton, 1, 4);

        Label registrationLabel = new Label("Rejestracja Na Wydarzenie");
        gridPane.add(registrationLabel, 2, 0);

        Button confirmRegistrationButton = new Button("Zarejestruj Użytkownika");
        gridPane.add(confirmRegistrationButton, 2, 1);

        Button rejectRegistrationButton = new Button("Odrzuć Użytkownika");
        gridPane.add(rejectRegistrationButton, 2, 2);

        addUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addUser.openRegisterForm();
            }
        });

        deleteUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.deleteUser(usersBox.getValue());
            }
        });

        resetPasswordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.resetPwd(usersBox.getValue());
            }
        });

        addEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                events.showAddEventView();
            }
        });

        deleteEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.deleteEvent(eventsBox.getValue());

            }
        });

        modifyEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editEventView.openEditEventView();
            }
        });

        confirmRegistrationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.confirmUser(usersBox.getValue(), eventsBox.getValue());
            }
        });

        rejectRegistrationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.unconfirmUser(usersBox.getValue(), eventsBox.getValue());
            }
        });

        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setScene(scene);
    }

    public void openAdminView() {
        primaryStage.show();
    }
}
