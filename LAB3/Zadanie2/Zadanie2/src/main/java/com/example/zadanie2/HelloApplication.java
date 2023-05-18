package com.example.zadanie2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Student Maciek = new Student("Maciek", "Tester", 123456, 3);
        Student Bartek = new Student("Bartek", "Tester", 123457, 3);
        Uni rok = new Uni();
        rok.addStudent(Maciek, new double[]{4.0, 4.5, 5.0});
        rok.addStudent(Bartek, new double[]{4.0, 2.0, 3.5});
        rok.getStudents();
        rok.deleteStudent(123456);
//        rok.deleteStudent(123456); teraz już nie zadziała, taki student nie istnieje
        rok.getStudents();
        rok.getStudentAverageGrade(123457);
        rok.addStudent(Maciek, new double[]{4.0, 4.5, 5.0});
        System.out.println("Średnia ocen całego roku: " +rok.getAllStudentsAverage());
    }

    public static void main(String[] args) {
        launch();
    }
}