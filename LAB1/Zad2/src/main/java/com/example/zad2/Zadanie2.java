package com.example.zad2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie2 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        List<Integer> list = new ArrayList<>();
        int cntr = 0;
        int distinctVal = 0;
        Scanner scanner = new Scanner(System.in);
        while(distinctVal < 15){
            System.out.println("Podaj liczbe: ");
            int num = scanner.nextInt();
            if(!list.contains(num)){
                distinctVal++;
            }
            cntr++;
            list.add(num);
            System.out.println("Ilosc unikalnych wartosci " +distinctVal);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}