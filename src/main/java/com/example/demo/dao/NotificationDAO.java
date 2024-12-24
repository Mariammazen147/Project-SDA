package com.example.demo.dao;

import com.example.demo.models.Notification;
import java.util.List;

public interface NotificationDAO {
    void saveNotification(Notification notification);
    Notification getNotificationById(int notificationId);
    List<Notification> getAllNotifications();
    void deleteNotification(int notificationId);
}
