package com.example.demo.dao;

import com.example.demo.models.Booking;
import java.util.List;

public interface BookingDAO {
    void saveBooking(Booking booking);
    Booking getBookingById(int bookingId);
    List<Booking> getAllBookings();
    void updateBooking(Booking booking);
    void deleteBooking(int bookingId);
    List<Booking> searchBookings(String status, Integer userId);

}