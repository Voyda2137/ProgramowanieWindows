package com.example.lab4;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditEventView {

    public void openEditEventView() {
        Database db = new Database();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ComboBox<String> eventComboBox = new ComboBox<>();
        eventComboBox.setPromptText("Wybierz Wydarzenie");
        eventComboBox.setItems(db.getEvents());;
        gridPane.add(eventComboBox, 0, 0);

        TextArea newEventName = new TextArea();
        newEventName.setPromptText("Zmień Nazwę Wydarzenia");
        newEventName.setPrefRowCount(5);
        gridPane.add(newEventName, 0, 1);

        TextArea agendaTextArea = new TextArea();
        agendaTextArea.setPromptText("Zmień Agendę");
        agendaTextArea.setPrefRowCount(5);
        gridPane.add(agendaTextArea, 0, 2);

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Zmień Datę");
        gridPane.add(datePicker, 0, 3);

        Button submitButton = new Button("Potwierdź");
        gridPane.add(submitButton, 0, 4);

        submitButton.setOnAction(event -> {
            String selectedEvent = eventComboBox.getValue();
            String agenda = agendaTextArea.getText();
            String eventDate = datePicker.getValue().toString();

            db.editEvent(
                    eventComboBox.getValue(),
                    newEventName.getText(),
                    agendaTextArea.getText(),
                    datePicker.getValue().toString()
            );
        });

        Scene scene = new Scene(gridPane, 310, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Event");
        stage.show();
    }
}
