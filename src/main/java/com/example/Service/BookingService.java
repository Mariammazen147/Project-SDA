package com.example.Service;


import org.springframework.stereotype.Service;
import com.example.Model.Booking;

//Created this only so I could make my code work 34an mhtagah
@Service
public class BookingService {
    public Booking createHotelBooking(String hotelName, int userId) {
        return new Booking(hotelName,userId);
    }
}
