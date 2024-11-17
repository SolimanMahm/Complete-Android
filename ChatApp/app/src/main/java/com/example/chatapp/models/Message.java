package com.example.chatapp.models;

public class Message {
    private String message;
    private String id;

    public Message(String id, String message) {
        this.message = message;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
