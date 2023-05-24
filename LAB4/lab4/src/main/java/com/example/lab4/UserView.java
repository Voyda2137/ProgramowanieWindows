package com.example.lab4;

import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;


public class UserView {
    private Stage userView;
    private Stage eventsView;

    public void openUserView(String user) {
        LoginForm alert = new LoginForm();
        if(userView == null){
            Database db = new Database();
            userView = new Stage();
            userView.setTitle("Witaj " + user + "!");

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setVgap(10);
            gridPane.setHgap(10);

            Label agendaLabel = new Label("Agenda:");
            Label agendaText = new Label();
            gridPane.add(agendaLabel, 0, 1);
            gridPane.add(agendaText, 1, 1);

            Label eventDateLabel3 = new Label("Termin wybranego wydarzenia:");
            Label eventDate = new Label();
            gridPane.add(eventDateLabel3, 0, 4);
            gridPane.add(eventDate, 1, 4);

            Label eventNameLabel = new Label("Nazwa wydarzenia:");
            ComboBox<String> eventNameComboBox = new ComboBox<>();
            eventNameComboBox.setItems(db.getEvents());
            eventNameComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    agendaText.setText(db.getAgenda(newValue));
                    eventDate.setText(db.getEventDate(newValue));
                }
            });
            gridPane.add(eventNameLabel, 0, 0);
            gridPane.add(eventNameComboBox, 1, 0);

            Label eventDateLabel = new Label("Data od:");
            DatePicker eventDatePicker = new DatePicker();
            gridPane.add(eventDateLabel, 0, 2);

            Button checkEventsButton = new Button("Sprawdź");

            gridPane.add(checkEventsButton, 1, 3);

            HBox hbox = new HBox(10);
            hbox.getChildren().addAll(eventDatePicker, checkEventsButton);
            gridPane.add(hbox, 1, 2);

            Label eventDateLabel2 = new Label("Data do:");
            DatePicker eventDatePicker2 = new DatePicker();
            gridPane.add(eventDateLabel2, 0, 3);
            gridPane.add(eventDatePicker2, 1, 3);

            checkEventsButton.setOnAction(e -> {
                if(eventsView == null) {
                    LocalDate startDate = eventDatePicker.getValue();
                    LocalDate endDate = eventDatePicker2.getValue();
                    if(startDate != null){
                        if(endDate != null){
                            openTableView(db.selectDataBetweenDates(startDate, endDate));
                            System.out.println("XD" + db.selectDataBetweenDates(startDate, endDate));
                            endDate = null;
                            startDate = null;
                        }
                        else {
                            openTableView(db.selectDataAfterDate(startDate));
                            System.out.println("XD2" + db.selectDataAfterDate(startDate));
                            startDate = null;
                        }
                    }
                    else{
                        if(endDate != null){
                            openTableView(db.selectDataBeforeDate(endDate));
                            System.out.println("XD3" + db.selectDataBeforeDate(endDate));
                            endDate = null;
                        }
                        else {
                            alert.showAlertLogin("Błędna data", "Nie wybrano żadnej daty!");
                        }
                    }
                }
            });

            Label participationTypeLabel = new Label("Typ uczestnictwa:");
            ComboBox<String> participationTypeComboBox = new ComboBox<>(FXCollections.observableArrayList(
                    "Słuchacz", "Autor", "Sponsor", "Organizator"));
            gridPane.add(participationTypeLabel, 0, 5);
            gridPane.add(participationTypeComboBox, 1, 5);

            Label cateringLabel = new Label("Wyżywienie:");
            ComboBox<String> cateringComboBox = new ComboBox<>(FXCollections.observableArrayList(
                    "Bez preferencji", "Wegetariańskie", "Bez glutenu"));
            gridPane.add(cateringLabel, 0, 6);
            gridPane.add(cateringComboBox, 1, 6);

            Button submitBtn = new Button("Zapisz na wydarzenie");
            submitBtn.setOnAction(e -> {
                db.appendDataToJSONArray(
                        user,
                        eventNameComboBox.getValue(),
                        agendaText.getText(),
                        LocalDate.parse(eventDate.getText()),
                        participationTypeComboBox.getValue(),
                        cateringComboBox.getValue()
                );
            });
            submitBtn.setMinWidth(150);

            gridPane.add(submitBtn, 1, 7, 2, 1);

            Scene scene = new Scene(gridPane, 700, 400);
            userView.setScene(scene);
            userView.setOnCloseRequest(event -> userView = null);
            userView.show();
        }
        }
    private void openTableView(ObservableList<Event> events) {
        eventsView = new Stage();

        TableColumn<Event, String> eventNameColumn = new TableColumn<>("Nazwa wydarzenia");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventNameColumn.setPrefWidth(200);

        TableColumn<Event, LocalDate> eventDateColumn = new TableColumn<>("Data wydarzenia");
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        eventDateColumn.setPrefWidth(200);

        TableView<Event> tableView = new TableView<>();
        tableView.getColumns().addAll(eventNameColumn, eventDateColumn);

        tableView.setItems(events);

        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToHeight(true);

        eventsView.setScene(new Scene(scrollPane));
        eventsView.setOnCloseRequest(event -> eventsView = null);
        eventsView.setTitle("Wydarzenia w wybranym zakresie");
        eventsView.show();
    }

}
