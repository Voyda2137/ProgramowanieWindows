package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateColorController {
    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Button goBack, submit;
    @FXML
    private TextArea red, green, blue, name;

    @FXML
    public void goBackHandler(ActionEvent event) throws IOException {
        Parent newViewParent = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // przechodzenie między widokami
        window.setScene(newViewScene);
        window.show();
    }

    @FXML
    public void createColor(ActionEvent event) throws IOException {
        try {
            int redVal = Integer.parseInt(red.getText());
            int greenVal = Integer.parseInt(green.getText());
            int blueVal = Integer.parseInt(blue.getText());
            String nameVal = name.getText();
            if (ColorManager.getInstance().colorsMap.containsKey(nameVal)) { // sprawdzam czy istnieje już taki kolor
                alert.setTitle("Zduplikowany kolor");
                alert.setHeaderText(null);
                alert.setContentText("Kolor o takiej nazwie już istnieje!");
                alert.showAndWait();
            }
                if (ColorCreator.isValidRGB(redVal, greenVal, blueVal)) {
                ColorCreator color = new ColorCreator(redVal, greenVal, blueVal);
                ColorManager.getInstance().addColor(nameVal, color);
            } else {
                alert.setTitle("Błędne wartości");
                alert.setHeaderText(null);
                alert.setContentText("Nieprawidłowe wartości RGB");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            alert.setTitle("Błędne wartości");
            alert.setHeaderText(null);
            alert.setContentText("Wartości muszą być liczbami całkowitymi");
            alert.showAndWait();
        }
    }
}


