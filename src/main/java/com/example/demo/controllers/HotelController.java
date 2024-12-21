package com.example.demo.controllers;

import com.example.demo.models.Hotel;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        hotelService.addHotel(hotel);
        return "Hotel added successfully!";
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable("id") int id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public String updateHotel(@PathVariable("id") int id, @RequestBody Hotel hotel) {
        hotel.setHotelId(id);
        hotelService.updateHotel(hotel);
        return "Hotel updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable("id") int id) {
        hotelService.deleteHotel(id);
        return "Hotel deleted successfully!";
    }
}
