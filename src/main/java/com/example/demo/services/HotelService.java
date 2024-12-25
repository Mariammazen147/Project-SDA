package com.example.demo.services;

import com.example.demo.models.Hotel;
import java.util.List;

public interface HotelService {
    String addHotel(Hotel hotel);
    Hotel getHotelById(int hotelId);
    List<Hotel> getAllHotels();
    String updateHotel(Hotel hotel);
    String deleteHotel(int hotelId);
}
