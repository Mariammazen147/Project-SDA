package com.example.demo.services;

import com.example.demo.models.Hotel;
import java.util.List;

public interface HotelService {
    void addHotel(Hotel hotel);
    Hotel getHotelById(int hotelId);
    List<Hotel> getAllHotels();
    void updateHotel(Hotel hotel);
    void deleteHotel(int hotelId);
}
