package com.example.zad3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    public static void printArr(int[][] arr){
        // odczytanie ilosci rzedow i macierzy,
        // macierze sa kwadratowe wiec nie ma roznicy czy uzywam arr.length, czy arr[0].length,
        // ale jest to prawidlowe rozwiazanie
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static void fillArr(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                arr[i][j] = (int)(Math.random() * 21) + -10; //losowanie liczby w zakresie -10 do 10
            }
        }
    }
    public static int[][] addArr(int[][] arr1, int[][] arr2){
        int[][] sum = new int[arr1.length][arr1[0].length];
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0 ; j < arr1[0].length; j++){
                sum[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return sum;
    }
    public static int[][] subArr(int[][] arr1, int[][] arr2){
        int[][] sub = new int[arr1.length][arr1[0].length];
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0 ; j < arr1[0].length; j++){
                sub[i][j] = arr1[i][j] - arr2[i][j];
            }
        }
        return sub;
    }
    // metoda do obliczenia pomnozonej komorki macierzy
    public static int multiplyCell(int[][] arr1, int[][] arr2, int row, int col) {
        int cell = 0;
        for (int i = 0; i < arr2.length; i++) {
            cell += arr1[row][i] * arr2[i][col]; //obliczenie komorki
        }
        return cell;
    }
    // metoda do mnozenia macierza opierajaca sie na multiplyCell
    public static int[][] multiplyArr(int[][] arr1, int[][] arr2){
        int[][] multiply = new int[arr1.length][arr1[0].length];
        for (int row = 0; row < multiply.length; row++) {
            for (int col = 0; col < multiply[row].length; col++) {
                multiply[row][col] = multiplyCell(arr1, arr2, row, col); // obliczenie komorki za pomoca multiplyCell
            }
        }
        return multiply;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wielkosc macierzy: ");
        int size = scanner.nextInt();
        int matrix1[][] = new int[size][size];
        int matrix2[][] = new int[size][size];
        fillArr(matrix1);
        fillArr(matrix2);
        printArr(addArr(matrix1, matrix2));
        printArr(subArr(matrix1, matrix2));
        printArr(multiplyArr(matrix1, matrix2));
    }

    public static void main(String[] args) {
        launch();
    }
}