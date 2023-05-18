package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class ColorCreator {
    private List<Integer> rgbValues;

    public ColorCreator(int redVal, int greenVal, int blueVal) {
        this.rgbValues = new ArrayList<>();
        this.rgbValues.add(redVal);
        this.rgbValues.add(greenVal);
        this.rgbValues.add(blueVal);
    }

    public List<Integer> getRgbValues() {
        return rgbValues;
    }
    public String getRed(){
        return rgbValues.get(0).toString();
    }
    public String getGreen(){
        return rgbValues.get(1).toString();
    }
    public String getBlue(){
        return rgbValues.get(2).toString();
    }

    public void setRgbValues(List<Integer> rgbValues) {
        this.rgbValues = rgbValues;
    }

    public static boolean isValidRGB(int red, int green, int blue) { // sprawdzam czy są to prawidłowe wartości RGB
        return red >= 0 && red <= 255 &&
                green >= 0 && green <= 255 &&
                blue >= 0 && blue <= 255;
    }
}
