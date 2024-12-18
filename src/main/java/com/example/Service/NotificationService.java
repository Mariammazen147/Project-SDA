package com.example.Service;


import com.example.Model.Notification;

//Created this only so i could make my code work 34an mhtagah
public class NotificationService {
    public Notification createNotification(String message, int userId){
        return new Notification(message, userId);
    }
}
