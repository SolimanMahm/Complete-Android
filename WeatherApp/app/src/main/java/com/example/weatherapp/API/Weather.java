package com.example.weatherapp.API;

public class Weather {
    private int id;
    private String main, description, icon;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return "https://download.spinetix.com/content/widgets/icons/weather/" + icon + ".png";
    }
}
