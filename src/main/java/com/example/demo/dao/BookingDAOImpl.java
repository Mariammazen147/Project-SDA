package com.example.demo.dao;

import com.example.demo.models.Booking;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=Travel_Agency;encrypt=true;trustServerCertificate=true;integratedSecurity=true";
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }

    @Override
    public void saveBooking(Booking booking) {
        String sql = "INSERT INTO Bookings (bookingId, userId, hotelId, eventId, bookingDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getBookingId()); // Use bookingId provided in the payload
            statement.setInt(2, booking.getUserId());
            statement.setInt(3, booking.getHotelId());
            statement.setInt(4, booking.getEventId());
            statement.setDate(5, Date.valueOf(booking.getBookingDate()));
            statement.setString(6, booking.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing saveBooking: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> searchBookings(String status, Integer userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Bookings WHERE 1=1");
        if (status != null) sql.append(" AND status = ?");
        if (userId != null) sql.append(" AND userId = ?");

        List<Booking> results = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            int index = 1;
            if (status != null) statement.setString(index++, status);
            if (userId != null) statement.setInt(index++, userId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("bookingId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setHotelId(rs.getInt("hotelId"));
                booking.setEventId(rs.getInt("eventId"));
                booking.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                booking.setStatus(rs.getString("status"));
                results.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    @Override
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM Bookings WHERE bookingId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookingId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("bookingId"));
                booking.setUserId(resultSet.getInt("userId"));
                booking.setHotelId(resultSet.getInt("hotelId"));
                booking.setEventId(resultSet.getInt("eventId"));
                booking.setBookingDate(resultSet.getDate("bookingDate").toLocalDate());
                booking.setStatus(resultSet.getString("status"));
                return booking;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Bookings";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("bookingId"));
                booking.setUserId(resultSet.getInt("userId"));
                booking.setHotelId(resultSet.getInt("hotelId"));
                booking.setEventId(resultSet.getInt("eventId"));
                booking.setBookingDate(resultSet.getDate("bookingDate").toLocalDate());
                booking.setStatus(resultSet.getString("status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public void updateBooking(Booking booking) {
        String sql = "UPDATE Bookings SET userId = ?, hotelId = ?, eventId = ?, bookingDate = ?, status = ? WHERE bookingId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getUserId());
            statement.setInt(2, booking.getHotelId());
            statement.setInt(3, booking.getEventId());
            statement.setDate(4, Date.valueOf(booking.getBookingDate()));
            statement.setString(5, booking.getStatus());
            statement.setInt(6, booking.getBookingId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(int bookingId) {
        String sql = "DELETE FROM Bookings WHERE bookingId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookingId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}