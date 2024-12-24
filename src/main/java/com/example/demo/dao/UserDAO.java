package com.example.demo.dao;

import com.example.demo.models.User;
import java.util.List;

public interface UserDAO {
    void saveUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);
}
