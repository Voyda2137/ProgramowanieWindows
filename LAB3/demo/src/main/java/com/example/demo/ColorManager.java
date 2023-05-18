package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class ColorManager {
    private static ColorManager instance;
    public Map<String, ColorCreator> colorsMap; // tworzę mapę, aby każdy kolor był unikatowy

    private ColorManager() {
        colorsMap = new HashMap<String, ColorCreator>();
    }

    public static ColorManager getInstance() { // zwracam instancję, a jeśli taka nie istnieje to tworzy nową
        if (instance == null) {
            instance = new ColorManager();
        }
        return instance;
    }

    public void addColor(String name, ColorCreator rgbValues) { // dodanie kolora o kluczu name do mapy kolorów
        colorsMap.put(name, rgbValues);
    }

    public Map<String, ColorCreator> getColors() {
        return colorsMap;
    }
}