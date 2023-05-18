package com.example.zadanie2;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int nr_indeksu, rok_st;
    private String imie, nazwisko;
    private List<Double> oceny = new ArrayList<>();
    public Student(String imie, String nazwisko, int nr_indeksu, int rok_st) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_indeksu = nr_indeksu;
        this.rok_st = rok_st;
    }
    public void setGrade(double ocena) {
        this.oceny.add(ocena);
    }
    public List<Double> getOceny() {
        return oceny;
    }
    public String getImie() {
        return imie;
    }
    public int getNr_indeksu() {
        return nr_indeksu;
    }


    public String getNazwisko() {
        return nazwisko;
    }

    public int getRok_st() {
        return rok_st;
    }

    public String getFields() {
        String fields = getImie() + " " + getNazwisko() + "\nNumer indeksu: " + getNr_indeksu() + "\nrok: " + getRok_st();

        if (!oceny.isEmpty()) {
            fields += "\nOceny: " + oceny;
        }
        return fields;
    }
}
