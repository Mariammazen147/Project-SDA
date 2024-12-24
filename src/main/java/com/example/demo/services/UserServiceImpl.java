package com.example.demo.services;

import com.example.demo.dao.NotificationDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.models.Notification;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public String addUser(User user) {
        userDAO.saveUser(user);
        Notification notification = new Notification();
        notification.setConcernedId(user.getUserId());
        notification.setMessage("User (ID: " + user.getUserId() + ") has been successfully registered.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }

    @Override
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public String updateUser(User user) {
        userDAO.updateUser(user);
        Notification notification = new Notification();
        notification.setConcernedId(user.getUserId());
        notification.setMessage("User (ID: " + user.getUserId() + ") has been successfully updated.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }

    @Override
    public String deleteUser(int userId) {
        userDAO.deleteUser(userId);
        Notification notification = new Notification();
        notification.setConcernedId(userId);
        notification.setMessage("User (ID: " + userId + ") has been successfully deleted.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }
}
