package com.example.demo.dao;

import com.example.demo.models.Event;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDAOImpl implements EventDAO {

    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=Travel_Agency;encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }

    @Override
    public void saveEvent(Event event) {
        String sql = "INSERT INTO Events (eventId, name, date, location, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, event.getEventId());
            statement.setString(2, event.getName());
            statement.setDate(3, Date.valueOf(event.getDate()));
            statement.setString(4, event.getLocation());
            statement.setFloat(5, event.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing saveEvent: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Event getEventById(int eventId) {
        String sql = "SELECT * FROM Events WHERE eventId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Event event = new Event();
                event.setEventId(resultSet.getInt("eventId"));
                event.setName(resultSet.getString("name"));
                event.setDate(resultSet.getDate("date").toLocalDate());
                event.setLocation(resultSet.getString("location"));
                event.setPrice(resultSet.getFloat("price"));
                return event;
            }
        } catch (SQLException e) {
            System.err.println("Error executing getEventById: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setEventId(resultSet.getInt("eventId"));
                event.setName(resultSet.getString("name"));
                event.setDate(resultSet.getDate("date").toLocalDate());
                event.setLocation(resultSet.getString("location"));
                event.setPrice(resultSet.getFloat("price"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error executing getAllEvents: " + e.getMessage());
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void updateEvent(Event event) {
        String sql = "UPDATE Events SET name = ?, date = ?, location = ?, price = ? WHERE eventId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getName());
            statement.setDate(2, Date.valueOf(event.getDate()));
            statement.setString(3, event.getLocation());
            statement.setFloat(4, event.getPrice());
            statement.setInt(5, event.getEventId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing updateEvent: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(int eventId) {
        String sql = "DELETE FROM Events WHERE eventId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing deleteEvent: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
