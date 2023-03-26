package com.example.zad4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Zadanie4 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        s1.add(10);
        s1.add(20);
        s1.add(30);
        s2.add(10);
        s2.add(15);
        s2.add(30);
        Set<Integer> union = new HashSet<>(s1);
        union.addAll(s2);
        Set<Integer> intersection = new HashSet<>(s1);
        intersection.retainAll(s2);
        Set<Integer> diff = new HashSet<>(s1);
        diff.removeAll(s2);
        Set<Integer> symm = new HashSet<>(s1);
        symm.addAll(s2); //żeby stworzyć różnicę symetryczną trzeba najpierw dodać oba zbiory i dokonać intersekcji
        symm.removeAll(intersection);
        System.out.println("Suma zbiorow: " +union);
        System.out.println("Czesc wspolna zbiorow: " +intersection);
        System.out.println("Roznica zbiorow: " +diff);
        System.out.println("Roznica symetryczna zbiorow: " +symm);
    }

    public static void main(String[] args) {
        launch();
    }
}