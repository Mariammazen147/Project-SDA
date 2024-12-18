package com.example.Model;


import java.util.Date;

import com.example.API.HotelAPI;
import com.example.Service.Database;
import com.example.demo.DemoApplication;


//Removed all controlling methods and added setter and getters to all attributes

public class Booking {
    private int bookingId;
    private long userId;
    private int hotelId;
    private int eventId;
    private Date bookingDate;
    private String status;
    //Added by mariam
    private String hotelName;

    public Booking(int hotelId, long userId, Date date) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.bookingDate = (date != null) ? date : new Date();
        System.out.println("Create new booking Object");
    }
    //5las 5lst

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    
    
    public long getUserId() {
        return userId;
    }

    public int getHotelId() {
        return hotelId;
    }
}
