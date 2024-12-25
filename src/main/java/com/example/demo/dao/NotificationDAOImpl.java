package com.example.demo.dao;

import com.example.demo.models.Notification;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationDAOImpl implements NotificationDAO {

    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=Project;encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }

    @Override
    public void saveNotification(Notification notification) {
        String getNextIdSQL = "SELECT ISNULL(MAX(notificationId), 0) + 1 AS nextId FROM Notifications";
        String insertSQL = "INSERT INTO Notifications (notificationId, concernedId, message) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement getNextIdStmt = connection.prepareStatement(getNextIdSQL);
             PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {

            // Get the next available notificationId
            ResultSet resultSet = getNextIdStmt.executeQuery();
            if (resultSet.next()) {
                int nextId = resultSet.getInt("nextId");
                notification.setNotificationId(nextId); // Set the next ID in the Notification object
            }

            // Insert the notification
            insertStmt.setInt(1, notification.getNotificationId());
            insertStmt.setInt(2, notification.getConcernedId());
            insertStmt.setString(3, notification.getMessage());
            insertStmt.executeUpdate();

            System.out.println("Notification added with ID: " + notification.getNotificationId());
        } catch (SQLException e) {
            System.err.println("Error executing saveNotification: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Notification getNotificationById(int notificationId) {
        String sql = "SELECT * FROM Notifications WHERE notificationId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(resultSet.getInt("notificationId"));
                notification.setConcernedId(resultSet.getInt("ConcernedId"));
                notification.setMessage(resultSet.getString("message"));
                return notification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(resultSet.getInt("notificationId"));
                notification.setConcernedId(resultSet.getInt("ConcernedId"));
                notification.setMessage(resultSet.getString("message"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public void deleteNotification(int notificationId) {
        String sql = "DELETE FROM Notifications WHERE notificationId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
