package com.example.demo.services;

import com.example.demo.models.Notification;
import java.util.List;

public interface NotificationService {
    void addNotification(Notification notification);
    Notification getNotificationById(int notificationId);
    List<Notification> getAllNotifications();
    void deleteNotification(int notificationId);
}
