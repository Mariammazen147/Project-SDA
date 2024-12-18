package com.example.API;

import com.example.Service.BookingService;
import com.example.Service.NotificationService;
import com.example.Model.Notification;
import com.example.Model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Object> getUserDashboard(@PathVariable int userId) {
        Booking booking = bookingService.createHotelBooking("Hotel XYZ", userId);
        Notification notification = notificationService.createNotification("Booking Confirmed", userId);
        return List.of(booking, notification);
    }
}
