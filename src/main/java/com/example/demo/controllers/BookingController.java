package com.example.demo.controllers;

import com.example.demo.models.Booking;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BoMokingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String addBooking(@RequestBody Booking booking) {
        bookingService.addBooking(booking);
        return "Booking added successfully!";
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable("id") int id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/search")
    public List<Booking> searchBookings(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer userId) {
        return bookingService.searchBookings(status, userId);
    }

    @PutMapping("/{id}")
    public String updateBooking(@PathVariable("id") int id, @RequestBody Booking booking) {
        booking.setBookingId(id);
        bookingService.updateBooking(booking);
        return "Booking updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully!";
    }
}