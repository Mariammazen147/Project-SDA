package com.example.demo.services;

import com.example.demo.models.User;
import java.util.List;

public interface UserService {
    String addUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    String updateUser(User user);
    String deleteUser(int userId);
}
