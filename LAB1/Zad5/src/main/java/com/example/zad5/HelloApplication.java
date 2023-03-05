package com.example.zad5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe: ");
        int num = scanner.nextInt();
        if(num > 1){
            for(int i = 2; i < num/2 + 1; i++){
                if(num % i == 0){
                    System.out.println("Podana liczba nie jest liczba pierwsza");
                    break; //jesli znaleziony zostanie dzielnik petla zatrzymuje sie
                }
                else{
                    System.out.println("Podana liczba jest liczba pierwsza");
                }
            }
        }
        else{
            System.out.println("Podana liczba nie jest liczba pierwsza");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}