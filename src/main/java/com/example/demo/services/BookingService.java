package com.example.demo.services;

import com.example.demo.models.Booking;

import java.util.List;

public interface BookingService {
    String addBooking(Booking booking);
    Booking getBookingById(int bookingId);
    List<Booking> getAllBookings();
    String updateBooking(Booking booking);
    String deleteBooking(int bookingId);
    List<Booking> searchBookings(Integer userId);
    String switchBookingStatus(int id);
}