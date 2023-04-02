package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainViewController {
    @FXML
    private Button createNewClr, displayClr, mixClrs;
    @FXML
    public void openCreateClrView(ActionEvent event) throws IOException {
        Parent newViewParent = FXMLLoader.load(getClass().getResource("create-color-view.fxml"));
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newViewScene);
        window.show();
    }
    @FXML
    public void openDisplayClrView(ActionEvent event) throws IOException {
        Parent newViewParent = FXMLLoader.load(getClass().getResource("display-color-view.fxml"));
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newViewScene);
        window.show();
    }
    @FXML
    public void openMixClrView(ActionEvent event) throws IOException {
        Parent newViewParent = FXMLLoader.load(getClass().getResource("mix-color-view.fxml"));
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newViewScene);
        window.show();
    }
}