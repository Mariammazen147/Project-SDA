package com.example.demo.dao;

import com.example.demo.models.Event;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDAOImpl implements EventDAO {

    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=Project;encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }

    @Override
    public void saveEvent(Event event) {
        String sql = "INSERT INTO Events (eventId, name, location, price, tickets) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, event.getEventId());
            statement.setString(2, event.getName());
            statement.setString(3, event.getLocation());
            statement.setFloat(4, event.getPrice());
            statement.setInt(5, event.getTickets());
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
                event.setLocation(resultSet.getString("location"));
                event.setPrice(resultSet.getFloat("price"));
                event.setTickets(resultSet.getInt("tickets"));
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
                event.setLocation(resultSet.getString("location"));
                event.setPrice(resultSet.getFloat("price"));
                event.setTickets(resultSet.getInt("tickets"));
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
        String sql = "UPDATE Events SET name = ?, location = ?, price = ?, tickets = ? WHERE eventId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getName());
            statement.setString(2, event.getLocation());
            statement.setFloat(3, event.getPrice());
            statement.setInt(4, event.getTickets());
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

    @Override
    public List<Event> getEventsByLocation(String location) {
        String sql = "SELECT * FROM Events WHERE location = ?";
        List<Event> events = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, location);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Event event = new Event();
                    event.setEventId(resultSet.getInt("eventId"));
                    event.setName(resultSet.getString("name"));
                    event.setLocation(resultSet.getString("location"));
                    event.setTickets(resultSet.getInt("tickets"));
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

}
