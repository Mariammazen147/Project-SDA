package com.example.Service;

import com.example.Model.User;

public class UserAccountService {
    public User registerUser(String name, String email, String password) {
        User newUser = new User(name,email,password);
        return newUser; //el mafrood teb2a connected to the database
    }
    public User loginUser(String email, String password) {
        //Check user Credentials (using el database instead of the quotations)
        if("user@example.com".equals(email)&& "password".equals(password)) {
            return new User("User name",password,email);
        }else{
            return null;
        }
    }
    public User updateProfile(User user, String newName, String newEmail) {
        //modify user data in database
        user.setName(newName);
        user.setEmail(newEmail);
        return user;
    }
}
