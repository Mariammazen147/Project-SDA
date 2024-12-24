package com.example.demo.models;

public class Notification {
    private int notificationId;
    private int concernedId;
    private String message;

    // Getters and Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getConcernedId() {
        return concernedId;
    }

    public void setConcernedId(int concernedId) {
        this.concernedId = concernedId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
