package com.example.zad3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Zadanie3 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe: ");
        int bin = scanner.nextInt();
        char[] arr = Integer.toBinaryString(bin).toCharArray();
        int helperVar = 1, i = 0, gaps = 0;
        while(i < arr.length){
            if(arr[i] == '1'){
                i++;
                helperVar = 1;
                while(helperVar == 1 && i < arr.length){
                    if (arr[i] == '0') {
                        i++;
                        while(helperVar == 1 && i < arr.length){
                            if(arr[i] == '1'){
                                gaps++;
                                helperVar = 0;
                            }
                            else if(arr[i] == '0'){
                                i++;
                            }
                        }
                    }
                else if(arr[i] == '1'){
                    i++;
                    }
                }
            }
            else if(arr[i] == '0'){
                i++;
            }
        }
        System.out.println("Ilosc dziur binarnych: " +gaps);
    }

    public static void main(String[] args) {
        launch();
    }
}