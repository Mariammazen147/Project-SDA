package com.example.demo.controllers;

import com.example.demo.models.Booking;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
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
            @RequestParam(required = false) Integer userId) {
        return bookingService.searchBookings(userId);
    }

    @PutMapping("/{id}")
    public String updateBooking(@PathVariable("id") int id, @RequestBody Booking booking) {
        booking.setBookingId(id);
        return bookingService.updateBooking(booking);
    }

    @PutMapping("/{id}/statusSwitch")
    public String switchBookingStatus(@PathVariable("id") int id) {
        return bookingService.switchBookingStatus(id);
    }


    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        return bookingService.deleteBooking(id);
    }
}