package com.example.zadanie2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Uni {
    private List<Student> students;

    private List<Double> possibleGrades = Arrays.asList(2.0, 3.0, 3.5, 4.0, 4.5, 5.0); //lista ocen

    public Uni() {
        students = new ArrayList<>();
    }
    public void getStudents() {
        for (Student s : students) {
            System.out.println(s.getFields() +"\n");
        }
    }
    public void addStudent(Student student, double[] oceny) throws IllegalArgumentException {
        for (Student s : students) {
            if (s.getNr_indeksu() == student.getNr_indeksu()) { //sprawdzam czy istnieje już taki student
                throw new IllegalArgumentException("Student o takim indeksie już istnieje!");
            }
        }
        if (oceny != null) { //sprawdzam czy parametr oceny jest przekazywany
            for (double ocena : oceny) {
                if (!possibleGrades.contains(ocena)) {
                    throw new IllegalArgumentException("Ocena musi być wartością ze zbioru: 2.0, 3.0, 3.5, 4.0, 4.5, 5.0");
                }
                student.setGrade(ocena); // dodaje ocenę do listy
            }
        }
        students.add(student); // dodaję studenta do listy
    }
    public void deleteStudent(int nr_indeksu) {
        boolean studentExists = false;
        for (Student s : students) {
            if(s.getNr_indeksu() == nr_indeksu) {
                students.remove(s);
                studentExists = true;
                break; //nie muszę dalej iterować, nr_indeksu jest unikatowe
            }
        }
        if(!studentExists) {
            throw new IllegalArgumentException("Student o indeksie: " + nr_indeksu +" nie istnieje!");
        }
    }
    public void getStudentAverageGrade(int nr_indeksu) {
        double gradeSum = 0.0;
        List<Double> grades;
        double averageGrade = 0.0;
        String student = "";
        boolean studentExists = false;
        for (Student s : students) {
            if(s.getNr_indeksu() == nr_indeksu) {
                student = s.getImie() + " " +s.getNazwisko();
                grades = s.getOceny();
                for(double g: grades) {
                    gradeSum += g;
                }
                averageGrade = gradeSum / grades.size();
                averageGrade = Math.round(averageGrade * 2.0) / 2.0; //zaokraglenie do .5 albo .0
                studentExists = true;
                break; //nie musze dalej iterować, nr_indeksu jest unikatowe
            }
        }
        if(!studentExists) {
            throw new IllegalArgumentException("Student o indeksie: " +nr_indeksu +" nie istnieje!");
        }
        else {
            System.out.println("Średnia ocen studenta " +student + " to " +averageGrade);
        }
    }
    public double getAllStudentsAverage() {
        double sum = 0.0;
        int gradesAmount = 0;
        for(Student s : students){
            if(!s.getOceny().isEmpty()){
                for(double ocena: s.getOceny()){
                    sum += ocena;
                    gradesAmount++;
                }
            }
        }
        if(gradesAmount > 0){
            sum = sum / gradesAmount;
            sum = Math.round(sum * 2.0) / 2.0; //zaokraglenie do .5 albo .0
            return sum;
        }
        else{
            throw new IllegalArgumentException("Student nie ma ocen!");
        }
    }
}