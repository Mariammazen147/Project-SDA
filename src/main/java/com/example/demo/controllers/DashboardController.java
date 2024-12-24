package com.example.demo.controllers;


import com.example.demo.models.Booking;
import com.example.demo.models.Notification;
import com.example.demo.services.BookingService;
import com.example.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping( "/bookings")
    public String getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        int size = bookings.size();
        String message = "There are " + size + " booking(s) in the system.";
        return  message;
    }

    @GetMapping( "/notifications")
    public String getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        int size = notifications.size();
        String message = "There are " + size + " notification(s) in the system.";
        return  message;
    }

}
