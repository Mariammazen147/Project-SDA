package com.example.demo.services;

import com.example.demo.dao.HotelDAO;
import com.example.demo.models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;

    @Override
    public void addHotel(Hotel hotel) {
        hotelDAO.saveHotel(hotel);
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        return hotelDAO.getHotelById(hotelId);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    @Override
    public void deleteHotel(int hotelId) {
        hotelDAO.deleteHotel(hotelId);
    }
}
