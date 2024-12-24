package com.example.demo.models;

public class Hotel {
    private int hotelId;
    private String name;
    private String location;
    private float pricePerNight;
    private int familyRoom;
    private int singleRoom;
    private int doubleRoom;

    // Getters and Setters
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getFamilyRoom() {
        return familyRoom;
    }

    public void setFamilyRoom(int familyRoom) {
        this.familyRoom = familyRoom;
    }

    public int getSingleRoom() {
        return singleRoom;
    }

    public void setSingleRoom(int singleRoom) {
        this.singleRoom = singleRoom;
    }

    public int getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(int doubleRoom) {
        this.doubleRoom = doubleRoom;
    }
}
