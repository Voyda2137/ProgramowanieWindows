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

public class MixColorController {
    int red1, green1, blue1, red2, green2, blue2;
    String name1, name2;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private TextArea color1Area, color2Area;
    @FXML
    private Label newColorNameArea;
    @FXML
    public void goBackHandler(ActionEvent event) throws IOException {
        Parent newViewParent = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newViewScene);
        window.show();
    }
    @FXML
    public void mixColor(ActionEvent event) throws IOException {
        String color1 = color1Area.getText();
        String color2 = color2Area.getText();
        boolean color1Existence = false, color2Existence = false;
        if (ColorManager.getInstance().colorsMap.containsKey(color1)) {
            color1Existence = true;
             red1 = Integer.parseInt(ColorManager.getInstance().colorsMap.get(color1).getRed());
             green1 = Integer.parseInt(ColorManager.getInstance().colorsMap.get(color1).getGreen());
             blue1 = Integer.parseInt(ColorManager.getInstance().colorsMap.get(color1).getBlue());
             name1 = color1;
        } else {
            alert.setTitle("Błędny kolor");
            alert.setHeaderText(null);
            alert.setContentText("Kolor 1 nie istnieje!");
            alert.showAndWait();
        }
        if (ColorManager.getInstance().colorsMap.containsKey(color2)) {
            color2Existence = true;
             red2 = Integer.parseInt(ColorManager.getInstance().colorsMap.get(color2).getRed());
             green2 = Integer.parseInt(ColorManager.getInstance().colorsMap.get(color2).getGreen());
             blue2 = Integer.parseInt(ColorManager.getInstance().colorsMap.get(color2).getBlue());
             name2 = color2;
        } else {
            alert.setTitle("Błędny kolor");
            alert.setHeaderText(null);
            alert.setContentText("Kolor 2 nie istnieje!");
            alert.showAndWait();
        }
        if(color1Existence && color2Existence){
            String newName = name1 + "-" + name2;
            if (ColorManager.getInstance().colorsMap.containsKey(newName)) {
                alert.setTitle("Zduplikowany kolor");
                alert.setHeaderText(null);
                alert.setContentText("Kolor o takiej nazwie już istnieje!");
                alert.showAndWait();
            }
            int newRed = (red1 + red2) / 2;
            int newGreen = (green1 + green2) / 2;
            int newBlue = (blue1 + blue2) / 2;
            ColorCreator color = new ColorCreator(newRed, newGreen, newBlue);
            ColorManager.getInstance().addColor(newName, color);
            newColorNameArea.setText(newName);
        }
    }
}
