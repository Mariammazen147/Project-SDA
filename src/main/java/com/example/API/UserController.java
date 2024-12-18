package com.example.API;

import com.example.Service.UserAccountService;
import com.example.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserAccountService userAccountService;

    //Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userAccountService.registerUser(user.getName(), user.getEmail(), user.getPassword());
    }

    //Login user
    @PostMapping("/login")
    public User loginUser(@RequestParam String email, @RequestParam String password) {
        return userAccountService.loginUser(email, password);
    }

    //Update user profile
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userAccountService.updateProfile(user, user.getName(), user.getEmail());
    }
}
