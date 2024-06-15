package com.example.toku.modules;


public class item {
    private String jpName, enName;
    private int image = 0, sound;

    public item(int sound, int image, String jpName, String enName) {
        this.sound = sound;
        this.image = image;
        this.jpName = jpName;
        this.enName = enName;
    }

    public item(int sound, String jpName, String enName) {
        this.jpName = jpName;
        this.enName = enName;
        this.sound = sound;
    }

    public int getSound() {
        return sound;
    }

    public int getImage() {
        return image;
    }

    public String getJpName() {
        return jpName;
    }

    public String getEnName() {
        return enName;
    }
}
