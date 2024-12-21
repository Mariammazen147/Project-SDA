package com.example.demo.dao;

import com.example.demo.models.Hotel;
import java.util.List;

public interface HotelDAO {
    void saveHotel(Hotel hotel);
    Hotel getHotelById(int hotelId);
    List<Hotel> getAllHotels();
    void updateHotel(Hotel hotel);
    void deleteHotel(int hotelId);
}
