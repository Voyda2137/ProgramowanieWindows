package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class ColorManager {
    private static ColorManager instance;
    public Map<String, ColorCreator> colorsMap;

    private ColorManager() {
        colorsMap = new HashMap<String, ColorCreator>();
    }

    public static ColorManager getInstance() {
        if (instance == null) {
            instance = new ColorManager();
        }
        return instance;
    }

    public void addColor(String name, ColorCreator rgbValues) {
        colorsMap.put(name, rgbValues);
    }

    public Map<String, ColorCreator> getColors() {
        return colorsMap;
    }
}