package com.example.Model;


import java.util.Date;

import com.example.API.HotelAPI;
import com.example.Service.Database;
import com.example.demo.DemoApplication;

public class Booking {
    private int bookingId;
    private int userId;
    private int hotelId;
    private int eventId;
    private Date bookingDate;
    private String status;
    //Added by mariam
    private String hotelName;

    public Booking(String hotelName, int userId) {
        this.hotelName = hotelName;
        this.userId = userId;
    }
    //5las 5lst

    public boolean createBooking(int userId, int hotelId, Date date)
    {

        this.userId = userId;
        this.hotelId = hotelId;
        this.bookingDate = (date != null) ? date : new Date();

       
        if(HotelAPI.bookHotel(hotelId, userId))
        {
            this.bookingId = DemoApplication.lastBookingId++;

            System.out.println("From booking class");
            System.out.println("Added booking to booking table for user with id: " + userId + " for hotel with id: " + hotelId + " booking number: " + bookingId);
            return true;
        }
        else
        {
            System.out.println("Booking Failed");
            return false;
        }

    }

    public String getBookingDetails() {
        String details = "Booking ID: " + bookingId + " userId: " + userId + " hotelid: " + hotelId + "." ;
        details += "\n" + HotelAPI.getHotelById(hotelId).getHotelDetails();
        return details;
    }

    
    public void cancelBooking()
    {
        System.out.println("Removing Booking from database by id");
        Database.removeBooking(bookingId);
    }
}
