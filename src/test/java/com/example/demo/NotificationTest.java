package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest{

    @Test
    public void testSendNotification(){
        Notification notification = new Notification(1, 100, "Initial Message", "2024-06-18");

        notification.sendNotification(200, "Test Message");

        assertEquals(1, notification.getNotificationId());
        assertEquals(200, notification.getBookingId());
        assertEquals("Test Message", notification.getMessage());
    }

    @Test
    public void testGetNotificationDetails(){
        Notification notification=new Notification(1, 101, "Hello", "2024-06-18");

        String details=notification.getNotificationDetails();

        assertTrue(details.contains("Notification ID: 1"));
        assertTrue(details.contains("Booking ID: 101"));
        assertTrue(details.contains("Message: Hello"));
        assertTrue(details.contains("Sent Date: 2024-06-18"));
    }
}
