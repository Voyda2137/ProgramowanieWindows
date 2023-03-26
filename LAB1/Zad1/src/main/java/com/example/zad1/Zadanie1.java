package com.example.zad1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;

import java.io.IOException;
import java.util.Scanner;

public class Zadanie1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    int num = -1;
    int randNum = rand.nextInt(15);
    int cntr = 0;
    while(num != randNum){
        System.out.println("Podaj liczbe: ");
        num = input.nextInt();
        if(num == randNum){
            System.out.println("Odgadles liczbe");
        }
        else if(num > randNum){
            System.out.println("Wylosowana liczba jest mniejsza");
        }
        else if (num < randNum){
            System.out.println("Wylosowana liczba jest wieksza");
        }
        cntr++;
        System.out.println("Ilosc prob " +cntr);
    }
    }

    public static void main(String[] args) {
        launch();
    }
}