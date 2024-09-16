package com.example.weatherapp.API;

public class Main {
    private double temp, feels_like, temp_min, temp_max;
    private int pressure, humidity, sea_level, grnd_level;

    public int getTemp() {
        return (int) Math.round(temp - 273.15);
    }

    public int getTemp_min() {
        return (int) Math.round(temp_min - 273.15);
    }

    public int getTemp_max() {
        return (int) Math.round(temp_max - 273.15);
    }
}
