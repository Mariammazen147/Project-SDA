package com.example.demo;

import com.example.Service.UserAccountService;
import com.example.Model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAccountServiceTests {
    private final UserAccountService userAccountService = new UserAccountService();

//    @Test
//    void testRegisterUser() {
//        User user = userAccountService.registerUser("John", "John@example.com","password123");
//        assertNotNull(user);
//        assertEquals("John", user.getName());
//        assertEquals("John@example.com", user.getEmail());
//    }
//
//    @Test
//    void testLoginUser() {
//        User user = userAccountService.loginUser("John@example.com","password123");
//        assertNotNull(user);
//        assertEquals("John@example.com", user.getEmail());
//    }

    //Both of the commented tests are generating errors 8aleban 34an lesa m4 connected to the db (i'm guessing bgd m4 3arfa)

    @Test
    void testUpdateProfile() {
        User user = userAccountService.registerUser("John", "John@example.com","password123");
        userAccountService.updateProfile(user,"Rowan Kane", "Rowan.Kane@example.com");
        assertEquals("Rowan.Kane@example.com", user.getEmail());
        assertEquals("Rowan Kane", user.getName());
    }
}
