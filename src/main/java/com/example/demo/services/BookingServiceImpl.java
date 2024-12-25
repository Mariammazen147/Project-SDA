package com.example.demo.services;

import com.example.demo.dao.*;
import com.example.demo.models.Booking;
import com.example.demo.models.Event;
import com.example.demo.models.Hotel;
import com.example.demo.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private MockApiEventDAO eventDAO;

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public String addBooking(Booking booking) {
        try {
            // Debug: Log the incoming booking
            System.out.println("Adding booking: " + booking);

            // Check if booking already exists
            if (bookingDAO.bookingExists(booking.getBookingId())) {
                throw new IllegalArgumentException("Booking ID already exists: " + booking.getBookingId());
            }

            // Fetch the hotel associated with the booking
            Hotel hotel = hotelDAO.getHotelById(booking.getHotelId());
            if (hotel == null) {
                throw new IllegalArgumentException("Hotel not found with ID: " + booking.getHotelId());
            }

            // Debug: Log fetched hotel
            System.out.println("Fetched hotel: " + hotel);

            // Check room availability and update room counts
            int roomType = booking.getRoomType();
            switch (roomType) {
                case 1: // Family Room
                    if (hotel.getFamilyRoom() > 0) {
                        hotel.setFamilyRoom(hotel.getFamilyRoom() - 1);
                    } else {
                        return "No available Family Room!";
                    }
                    break;
                case 2: // Single Room
                    if (hotel.getSingleRoom() > 0) {
                        hotel.setSingleRoom(hotel.getSingleRoom() - 1);
                    } else {
                        return "No available Single Room!";
                    }
                    break;
                case 3: // Double Room
                    if (hotel.getDoubleRoom() > 0) {
                        hotel.setDoubleRoom(hotel.getDoubleRoom() - 1);
                    } else {
                        return "No available Double Room!";
                    }
                    break;
                default:
                    return "Invalid room type!";
            }
            hotelDAO.updateHotel(hotel);

            // Save the booking
            bookingDAO.saveBooking(booking);

            // Create and save a confirmation notification
            Notification notification = new Notification();
            notification.setConcernedId(booking.getBookingId());
            notification.setMessage("Dear User (ID: " + booking.getUserId() + ") Your booking (ID: " + booking.getBookingId() + ") has been successfully created.");
            notificationDAO.saveNotification(notification);

            // Fetch events and hotels in the same location excluding the booked ones
            String location = hotel.getLocation();
            List<Hotel> nearbyHotels = new ArrayList<>(hotelDAO.getHotelsByLocation(location));
            List<Event> nearbyEvents = new ArrayList<>(eventDAO.getEventsByLocation(location));


            System.out.println("Fetching nearby hotels for location: " + location);
            System.out.println("Nearby Hotels (before filtering): " + nearbyHotels);

            System.out.println("Fetching nearby events for location: " + location);
            System.out.println("Nearby Events (before filtering): " + nearbyEvents);


            // Filter out the booked hotel and events
            nearbyHotels.removeIf(h -> h.getHotelId() == booking.getHotelId());
            nearbyEvents.removeIf(e -> e.getEventId() == booking.getEventId());

            // Build a message listing nearby hotels and events
            StringBuilder additionalInfo = new StringBuilder();
            additionalInfo.append("\nNearby Hotels in ").append(location).append(": ");
            for (Hotel nearbyHotel : nearbyHotels) {
                additionalInfo.append("\n - ").append(nearbyHotel.getName());
            }

            additionalInfo.append("\nNearby Events in ").append(location).append(": ");
            for (Event nearbyEvent : nearbyEvents) {
                additionalInfo.append("\n - ").append(nearbyEvent.getName());
            }

            // Combine the confirmation message and the additional info
            return notification.getMessage() + additionalInfo.toString();

        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return "An error occurred while adding the booking: " + e.getMessage();
        }
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
    public String updateBooking(Booking booking) {
        bookingDAO.updateBooking(booking);
        Notification notification = new Notification();
        notification.setConcernedId(booking.getBookingId());
        notification.setMessage("Dear User (ID: " + booking.getUserId() + ") Your booking (ID: " + booking.getBookingId() + ") has been successfully updated.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }

    @Override
    public String deleteBooking(int bookingId) {
        bookingDAO.deleteBooking(bookingId);
        Notification notification = new Notification();
        notification.setConcernedId(bookingId);
        notification.setMessage("Booking (ID: " + bookingId + ") has been successfully deleted.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }

    @Override
    public List<Booking> searchBookings(Integer userId) {
        return bookingDAO.searchBookings(userId);
    }

    @Override
    public String switchBookingStatus(int bookingId) {
        try {
            // Fetch the booking by ID
            Booking booking = bookingDAO.getBookingById(bookingId);
            if (booking == null) {
                throw new IllegalArgumentException("Booking not found with ID: " + bookingId);
            }

            // Check current status and toggle
            boolean currentStatus = booking.getStatus();
            if (currentStatus) {
                cancelBooking(bookingId);
            } else {
                confirmBooking(bookingId);
            }

            booking.setStatus(!currentStatus);
            bookingDAO.updateBooking(booking);

            // Create notification
            Notification notification = new Notification();
            notification.setConcernedId(bookingId);
            String message = booking.getStatus()
                    ? "Your booking (ID: " + bookingId + ") has been confirmed."
                    : "Your booking (ID: " + bookingId + ") has been cancelled.";
            notification.setMessage(message);
            notificationDAO.saveNotification(notification);

            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while switching booking status: " + e.getMessage();
        }
    }

    public void cancelBooking(int bookingId) {
        // Fetch the booking by ID
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Booking not found with ID: " + bookingId);
            return;
        }

        // Get the hotel and event information from the booking
        Hotel hotel = hotelDAO.getHotelById(booking.getHotelId());
        Event event = eventDAO.getEventById(booking.getEventId());

        if (event != null) {
            event.setTickets(event.getTickets() + 1);  // Add a ticket back when canceled
            eventDAO.updateEvent(event);
        }

        // Update the hotel room availability based on the room type when canceling
        int roomType = booking.getRoomType();
        switch (roomType) {
            case 1: // Family Room
                hotel.setFamilyRoom(hotel.getFamilyRoom() + 1);
                break;
            case 2: // Single Room
                hotel.setSingleRoom(hotel.getSingleRoom() + 1);
                break;
            case 3: // Double Room
                hotel.setDoubleRoom(hotel.getDoubleRoom() + 1);
                break;
            default:
                System.out.println("Invalid room type for cancellation.");
                return;
        }

        // Update the hotel data with the new availability
        hotelDAO.updateHotel(hotel);
        System.out.println("Booking (ID: " + bookingId + ") has been cancelled.");
    }

    public void confirmBooking(int bookingId) {
        // Fetch the booking by ID
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Booking not found with ID: " + bookingId);
            return;
        }

        // Get the hotel and event information from the booking
        Hotel hotel = hotelDAO.getHotelById(booking.getHotelId());
        Event event = eventDAO.getEventById(booking.getEventId());

        if (event != null) {
            event.setTickets(event.getTickets() - 1);  // Subtract a ticket when confirmed
            eventDAO.updateEvent(event);
        }

        // Update the hotel room availability based on the room type when confirming
        int roomType = booking.getRoomType();
        switch (roomType) {
            case 1: // Family Room
                hotel.setFamilyRoom(hotel.getFamilyRoom() - 1);
                break;
            case 2: // Single Room
                hotel.setSingleRoom(hotel.getSingleRoom() - 1);
                break;
            case 3: // Double Room
                hotel.setDoubleRoom(hotel.getDoubleRoom() - 1);
                break;
            default:
                System.out.println("Invalid room type for confirmation.");
                return;
        }

        // Update the hotel data with the new availability
        hotelDAO.updateHotel(hotel);
        System.out.println("Booking (ID: " + bookingId + ") has been confirmed.");
    }

}