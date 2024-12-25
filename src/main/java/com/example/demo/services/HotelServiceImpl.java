package com.example.demo.services;

import com.example.demo.dao.HotelDAO;
import com.example.demo.dao.NotificationDAO;
import com.example.demo.models.Hotel;
import com.example.demo.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public String addHotel(Hotel hotel) {
        hotelDAO.saveHotel(hotel);
        Notification notification = new Notification();
        notification.setConcernedId(hotel.getHotelId());
        notification.setMessage("Hotel (ID: " + hotel.getHotelId() + ") has been successfully created.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
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
    public String updateHotel(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
        Notification notification = new Notification();
        notification.setConcernedId(hotel.getHotelId());
        notification.setMessage("Hotel (ID: " + hotel.getHotelId() + ") has been successfully updated.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }

    @Override
    public String deleteHotel(int hotelId) {
        hotelDAO.deleteHotel(hotelId);
        Notification notification = new Notification();
        notification.setConcernedId(hotelId);
        notification.setMessage("Hotel (ID: " + hotelId + ") has been successfully deleted.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }

}
