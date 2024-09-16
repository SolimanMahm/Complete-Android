package com.example.weatherapp.API;

import java.util.ArrayList;

public class WeatherModel {
    private Coord coord;
    private ArrayList<Weather> weather = new ArrayList<Weather>();
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int code;
    private String message;

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
