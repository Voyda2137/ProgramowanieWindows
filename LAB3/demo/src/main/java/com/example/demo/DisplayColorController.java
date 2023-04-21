package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayColorController {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private TextArea getColorArea;
    @FXML
    private Label red, green, blue, name;
    @FXML
    public void goBackHandler(ActionEvent event) throws IOException {
        Parent newViewParent = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // przechodzenie między widokami
        window.setScene(newViewScene);
        window.show();
    }
    @FXML
    public void displayColor(ActionEvent event) throws IOException {
        String passedName = getColorArea.getText();
            if (ColorManager.getInstance().colorsMap.containsKey(passedName)) {
                red.setText(ColorManager.getInstance().colorsMap.get(passedName).getRed());
                green.setText(ColorManager.getInstance().colorsMap.get(passedName).getGreen());
                blue.setText(ColorManager.getInstance().colorsMap.get(passedName).getBlue());
                name.setText(passedName);
            } else {
                alert.setTitle("Błędny kolor");
                alert.setHeaderText(null);
                alert.setContentText("Ten kolor nie istnieje!");
                alert.showAndWait();               }
    }
}
