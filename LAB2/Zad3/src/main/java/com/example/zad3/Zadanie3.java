package com.example.zad3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Zadanie3 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wielkosc macierzy: ");
        int size = scanner.nextInt();
        int matrix1[][] = new int[size][size];
        int matrix2[][] = new int[size][size];
        MatrixOperations.fillArr(matrix1);
        MatrixOperations.fillArr(matrix2);
        MatrixOperations.printArr(MatrixOperations.addArr(matrix1, matrix2));
        MatrixOperations.printArr(MatrixOperations.subArr(matrix1, matrix2));
        MatrixOperations.printArr(MatrixOperations.multiplyArr(matrix1, matrix2));
    }

    public static void main(String[] args) {
        launch();
    }
}