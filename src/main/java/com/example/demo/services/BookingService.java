package com.example.demo.services;

import com.example.demo.models.Booking;
import java.util.List;

public interface BookingService {
    void addBooking(Booking booking);
    Booking getBookingById(int bookingId);
    List<Booking> getAllBookings();
    void updateBooking(Booking booking);
    void deleteBooking(int bookingId);
    List<Booking> searchBookings(String status, Integer userId);
}