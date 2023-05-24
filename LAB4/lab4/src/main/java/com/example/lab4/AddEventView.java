package com.example.lab4;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddEventView {
    public void showAddEventView() {
        Database db = new Database();
        Stage stage = new Stage();
        stage.setTitle("Add Event");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label nameLabel = new Label("Nazwa Wydarzenia:");
        TextField nameTextField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);

        Label agendaLabel = new Label("Agenda:");
        TextField agendaTextField = new TextField();
        gridPane.add(agendaLabel, 0, 1);
        gridPane.add(agendaTextField, 1, 1);

        Label eventDateLabel = new Label("Data wydarzenia:");
        DatePicker eventDatePicker = new DatePicker();
        gridPane.add(eventDateLabel, 0, 2);
        gridPane.add(eventDatePicker, 1, 2);

        Button addEventButton = new Button("Dodaj Wydarzenie");
        gridPane.add(addEventButton, 1, 3);

        addEventButton.setOnAction(event -> {

            db.addEvent(nameTextField.getText(),
                    agendaTextField.getText(),
                    LocalDate.parse(eventDatePicker.getValue().toString()));

            nameTextField.clear();
            agendaTextField.clear();
            eventDatePicker.setValue(null);
        });

        Scene scene = new Scene(gridPane, 340, 200);
        stage.setScene(scene);
        stage.show();
    }
}
