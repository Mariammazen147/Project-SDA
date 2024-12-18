package com.example.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//Removed all controlling methods and added setter and getters to all attributes

@Entity
public class Hotel {
    @Id
    private int hotelId;
    private String name;
    private String location;
    private float pricePerNight;
    private int availableRooms;
    

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public boolean checkRoomsAvailability()
    {
        if(availableRooms > 0)
        {
            return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }
}
