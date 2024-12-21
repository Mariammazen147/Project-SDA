package com.example.demo.services;

import com.example.demo.dao.BookingDAO;
import com.example.demo.models.Booking;
import com.example.demo.dao.NotificationDAO;
import com.example.demo.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public void addBooking(Booking booking) {
        bookingDAO.saveBooking(booking);
        Notification notification = new Notification();
        notification.setNotificationId(booking.getBookingId()); // Use bookingId as notificationId
        notification.setBookingId(booking.getBookingId());
        notification.setMessage("Your booking (ID: " + booking.getBookingId() + ") has been successfully created.");
        notificationDAO.saveNotification(notification);
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingDAO.updateBooking(booking);

    }

    @Override
    public void deleteBooking(int bookingId) {
        bookingDAO.deleteBooking(bookingId);
    }

    @Override
    public List<Booking> searchBookings(String status, Integer userId) {
        return bookingDAO.searchBookings(status, userId);
    }
}