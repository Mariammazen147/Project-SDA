package com.example.demo.dao;

import com.example.demo.models.Hotel;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelDAOImpl implements HotelDAO {

    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=Project;encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }

    @Override
    public void saveHotel(Hotel hotel) {
        String sql = "INSERT INTO Hotels (hotelId, name, location, pricePerNight, FamilyRoom, SingleRoom, DoubleRoom) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotel.getHotelId());
            statement.setString(2, hotel.getName());
            statement.setString(3, hotel.getLocation());
            statement.setFloat(4, hotel.getPricePerNight());
            statement.setInt(5, hotel.getFamilyRoom());
            statement.setInt(6, hotel.getSingleRoom());
            statement.setInt(7, hotel.getDoubleRoom());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        String sql = "SELECT * FROM Hotels WHERE hotelId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(resultSet.getInt("hotelId"));
                hotel.setName(resultSet.getString("name"));
                hotel.setLocation(resultSet.getString("location"));
                hotel.setPricePerNight(resultSet.getFloat("pricePerNight"));
                hotel.setFamilyRoom(resultSet.getInt("FamilyRoom"));
                hotel.setSingleRoom(resultSet.getInt("SingleRoom"));
                hotel.setDoubleRoom(resultSet.getInt("DoubleRoom"));
                return hotel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM Hotels";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(resultSet.getInt("hotelId"));
                hotel.setName(resultSet.getString("name"));
                hotel.setLocation(resultSet.getString("location"));
                hotel.setPricePerNight(resultSet.getFloat("pricePerNight"));
                hotel.setFamilyRoom(resultSet.getInt("FamilyRoom"));
                hotel.setSingleRoom(resultSet.getInt("SingleRoom"));
                hotel.setDoubleRoom(resultSet.getInt("DoubleRoom"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    @Override
    public void updateHotel(Hotel hotel) {
        String sql = "UPDATE Hotels SET name = ?, location = ?, pricePerNight = ?, FamilyRoom = ?, SingleRoom = ?, DoubleRoom = ? WHERE hotelId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setFloat(3, hotel.getPricePerNight());
            statement.setInt(4, hotel.getFamilyRoom());
            statement.setInt(5, hotel.getSingleRoom());
            statement.setInt(6, hotel.getDoubleRoom());
            statement.setInt(7, hotel.getHotelId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHotel(int hotelId) {
        String sql = "DELETE FROM Hotels WHERE hotelId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Hotel> getHotelsByLocation(String location) {
        String sql = "SELECT * FROM Hotels WHERE location = ?";
        List<Hotel> hotels = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, location);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setHotelId(resultSet.getInt("hotelId"));
                    hotel.setName(resultSet.getString("name"));
                    hotel.setLocation(resultSet.getString("location"));
                    hotel.setPricePerNight(resultSet.getFloat("pricePerNight"));
                    hotel.setFamilyRoom(resultSet.getInt("FamilyRoom"));
                    hotel.setSingleRoom(resultSet.getInt("SingleRoom"));
                    hotel.setDoubleRoom(resultSet.getInt("DoubleRoom"));
                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }



}
