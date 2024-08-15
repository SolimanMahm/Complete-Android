package com.example.newsapp.models;

public class Category {
    private String text;
    private int image;

    public Category(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
