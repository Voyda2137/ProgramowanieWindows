package com.example.zad1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Zadanie1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Lista zacznie byc sortowana po podaniu 2 liczb, zeby wyjsc z petli podaj 2137 ");
        Scanner scanner = new Scanner(System.in);
        BubbleSort bubble = new BubbleSort();
        ArrayList<Integer> list = new ArrayList<>();
        int input = 0;
        while(input != 2137){
            System.out.println("Podaj liczbe: ");
            input = scanner.nextInt();
            list.add(input);
            if(list.size() > 1){ //sortuje dopiero kiedy podane beda wiecej niz 2 liczby
                bubble.bubbleSort(list);
                bubble.printArray(list);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}