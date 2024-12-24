package com.example.demo.controllers;

import com.example.demo.models.Notification;
import com.example.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String addNotification(@RequestBody Notification notification) {
        notificationService.addNotification(notification);
        return "Notification added successfully!";
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable("id") int id) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @DeleteMapping("/{id}")
    public String deleteNotification(@PathVariable("id") int id) {
        notificationService.deleteNotification(id);
        return "Notification deleted successfully!";
    }
}
