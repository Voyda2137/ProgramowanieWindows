package com.example.zad2;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wielkosc tablicy: ");
        int size = scanner.nextInt();
        double[] arr = new double[size];
        List<Double> values = Arrays.asList(2.0, 3.0, 3.5, 4.0, 4.5, 5.0);
        Random rand = new Random();
        System.out.println("Wylosowana tablica:");
        for(int i = 0; i < arr.length; i++){
        arr[i] = values.get(rand.nextInt(values.size())); //dla kazdego indeksu tablicy losuje wartosc z listy wartosci
        System.out.print(arr[i] + " ");
        }
        double avg = 0;
        for(double el: arr){ //sumowanie wszystkich wartosci z tablicy
            avg += el;
        }
        avg = avg / arr.length; // wyliczenie sredniej
        System.out.println("\nSrednia wartosc w tablicy: " +avg);
        double max = arr[0];
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) { //wyszukanie maksymalnej wartosci
                max = arr[i];
            }
            if(arr[i] < min){ //wyszukanie minimalnej wartosci
                min = arr[i];
            }
        }
        System.out.println("Wartosc maksymalna: " + max);
        System.out.println("Wartosc minimalna: " + min);
        List<Double> higher = new ArrayList<>();
        List<Double> lower = new ArrayList<>();
        for(double el: arr){
            if(el > avg){
                higher.add(el);
            }
            else if(el < avg){
                lower.add(el);
            }
        }
        System.out.println("Wartosci wieksze od sredniej: ");
        for(double el: higher){
            System.out.print(el +" ");
        }
        System.out.println("\nWartosci mniejsze od sredniej: ");
        for(double el: lower){
            System.out.print(el + " ");
        }
        System.out.println("");
        boolean visited[] = new boolean[arr.length];
        Arrays.fill(visited, false);
        for (int i = 0; i < arr.length; i++) {

            // Skipowanie wartosci jesli juz zostala znaleziona
            if (visited[i] == true)
                continue;

            // Obliczenie czestotliwosci
            int count = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    visited[j] = true;
                    count++;
                }
            }
            System.out.println("Liczba: " + arr[i] + " Czestotliwosc: " + count);
        }
        double standardDeviation = 0.0;
        double sum = 0.0;
        for (double el : arr) {
            sum += el;
        }
        for (double el : arr) {
            standardDeviation += Math.pow(el - avg, 2);
        }
        System.out.print("Srednie odchylenie standardowe: " +Math.sqrt(standardDeviation / arr.length));
    }


    public static void main(String[] args) {
        launch();
    }
}