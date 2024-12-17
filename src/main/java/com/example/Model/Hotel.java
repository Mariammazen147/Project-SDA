package com.example.Model;

public class Hotel {
    private int hotelId;
    private String name;
    private String location;
    private float pricePerNight;
    private int availableRooms;
    
    public String getHotelDetails()
    {
        String details = "Hotel ID: " + hotelId + " name: " + name + " located at: " + location + "." ;
        return details;
    
    }

    public boolean checkRoomsAvailability()
    {
        if(availableRooms > 0)
        {
            return true;
        }
        return false;
    }

    public void updateHotelDetails(String name, String location, float pricePerNight)
    {
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
    }
}
