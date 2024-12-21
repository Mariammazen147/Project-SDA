package com.example.demo.services;

import com.example.demo.models.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);
}
