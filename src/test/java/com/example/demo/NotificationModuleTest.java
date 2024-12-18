package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationModuleTest{

    @Test
    public void testQueueNotification(){
        NotificationModule module = new NotificationModule();
        Notification notification = new Notification(1, 200, "Queue Test", "2024-06-18");

        module.queueNotification(notification);

        assertEquals("Notification is in the queue.", module.getNotificationStatus(1));
    }

    @Test
    public void testSendNotification(){
        NotificationModule module=new NotificationModule();
        Notification notification=new Notification(1, 201, "Send Test", "2024-06-18");

        module.queueNotification(notification);
        module.sendNotification(notification);

        assertEquals("Notification not found in the queue.", module.getNotificationStatus(1));
    }

    @Test
    public void testLoadNotificationTemplate(){
        NotificationTemplate template = new NotificationTemplate(1, "Welcome", "Email", "Subject", "2024-06-18");
        template.createTemplate("Welcome Message", "Email", "Welcome", "2024-06-18");

        NotificationModule module = new NotificationModule();
        NotificationTemplate loadedTemplate = module.loadNotificationTemplate(1);

        assertNotNull(loadedTemplate);
        assertEquals("Welcome Message", loadedTemplate.getContent());
    }
}
