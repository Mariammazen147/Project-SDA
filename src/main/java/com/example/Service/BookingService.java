package com.example.Service;


import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.API.HotelAPI;
import com.example.Model.Booking;
import com.example.Model.Hotel;
import com.example.demo.DemoApplication;

@Service
public class BookingService {
    //implementation for createbooking hotel
    public Booking createHotelBooking(int hotelId, long userId, Date date) {
        //validate user existance later
        if(HotelAPI.getHotelById(hotelId) != null)
        {
            Booking booking = new Booking(hotelId, userId, date);
            System.out.println("In the booking service check if booking can be processed");
            if(HotelAPI.bookHotel(hotelId, userId))
            {
                booking.setBookingId(DemoApplication.lastBookingId);
                DemoApplication.lastBookingId++;
                System.out.println("Added booking to booking table for user with id: " + userId + " for hotel with id: " + hotelId + " booking number: " + booking.getBookingId());
                return booking;
            }
            else
            {
                System.out.println("Booking Failed");
            }
        }
        
        return null;
    }

    //Moved updateHotel() from Hotel to seperate controlling methods from entity
    public boolean updateHotelDetails(int hotelId, String name, String location, float pricePerNight)
    {
        System.out.println("Fetching hotel from HotelAPI with id:" + hotelId + ".");
        Hotel hotel = HotelAPI.getHotelById(hotelId);
        if(hotel != null)
        {
            System.out.println("Hotel with id: " + hotelId + "found.");
            hotel.setName(name);
            hotel.setLocation(location);
            hotel.setPricePerNight(pricePerNight);
            System.out.println("Update hotel Entity in the Database.");
            HotelAPI.updateHotelById(hotelId, hotel);
            return true;
        }
        return false;
        
    }
    //Moved getHotelDetails() from Hotel to seperate controlling methods from entity

    public String getHotelDetails(int hotelId)
    {
        Hotel hotel = HotelAPI.getHotelById(hotelId);
        if(hotel != null)
        {
            String details = "Hotel ID: " + hotelId + " name: " + hotel.getName() + " located at: " + hotel.getLocation() + "." ;
            return details;
        }

        return "Hotel does not exist";
        
    
    }


    //Moved getBookingDetails() from Booking to seperate controlling methods from entity

    public String getHotelBookingDetails(int bookingId) {

        Booking booking = BookingAPI.getBookingById(bookingId);

        if(booking != null)
        {
            String details = "Booking ID: " + bookingId + " userId: " + booking.getUserId() + " hotelid: " + booking.getHotelId() + "." ;
            details += "\n" + getHotelDetails(booking.getHotelId());
            return details;
        }

        return "Booking does not exit";
        
    }


    //Moved cancelBooking() from Booking to seperate controlling methods from entity

    public void cancelBooking(int bookingId)
    {
        System.out.println("Removing Booking from database by id");
        Database.removeBooking(bookingId);
    }

}
