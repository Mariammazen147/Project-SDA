package com.example.demo.dao;

import com.example.demo.models.Booking;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=Project;encrypt=true;trustServerCertificate=true;integratedSecurity=true";
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }

    @Override
    public void saveBooking(Booking booking) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Project;encrypt=true;trustServerCertificate=true;integratedSecurity=true")) {
            String query = "INSERT INTO Bookings (bookingId, userId, hotelId, eventId, roomType, status) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, booking.getBookingId());
                statement.setInt(2, booking.getUserId());
                statement.setInt(3, booking.getHotelId());
                statement.setInt(4, booking.getEventId());
                statement.setInt(5, booking.getRoomType());
                statement.setBoolean(6, booking.getStatus());


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Booking successfully added.");
                } else {
                    System.out.println("Failed to add booking.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> searchBookings(Integer userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Bookings WHERE 1=1");
        if (userId != null) sql.append(" AND userId = ?");

        List<Booking> results = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            int index = 1;
            if (userId != null) statement.setInt(index++, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("bookingId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setHotelId(rs.getInt("hotelId"));
                booking.setEventId(rs.getInt("eventId"));
                booking.setStatus(rs.getBoolean("status"));
                booking.setRoomType(rs.getInt("roomType"));
                results.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean bookingExists(int bookingId) {
        String query = "SELECT COUNT(*) FROM Bookings WHERE bookingId = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the bookingId parameter
            statement.setInt(1, bookingId);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Return true if the count is greater than 0
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return false if no record is found or an error occurs
        return false;
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
                booking.setStatus(resultSet.getBoolean("status"));
                booking.setRoomType(resultSet.getInt("roomType"));
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
                booking.setStatus(resultSet.getBoolean("status"));
                booking.setRoomType(resultSet.getInt("roomType"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public void updateBooking(Booking booking) {
        String sql = "UPDATE Bookings SET userId = ?, hotelId = ?, eventId = ?, status = ?, roomType = ? WHERE bookingId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getUserId());
            statement.setInt(2, booking.getHotelId());
            statement.setInt(3, booking.getEventId());
            statement.setBoolean(4, booking.getStatus());
            statement.setInt(5, booking.getRoomType());
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