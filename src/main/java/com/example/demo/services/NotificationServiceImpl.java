package com.example.demo.services;

import com.example.demo.dao.NotificationDAO;
import com.example.demo.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public void addNotification(Notification notification) {
        notificationDAO.saveNotification(notification);
    }

    @Override
    public Notification getNotificationById(int notificationId) {
        return notificationDAO.getNotificationById(notificationId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationDAO.getAllNotifications();
    }

    @Override
    public void deleteNotification(int notificationId) {
        notificationDAO.deleteNotification(notificationId);
    }
}
