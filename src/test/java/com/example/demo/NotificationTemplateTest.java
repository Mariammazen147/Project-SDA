package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTemplateTest{

    @Test
    public void testCreateTemplate(){
        NotificationTemplate template = new NotificationTemplate(1, "Content", "Email", "Subject", "2024-06-18");
        template.createTemplate("New Content", "SMS", "New Subject", "2024-06-18");

        assertEquals("New Content", template.getContent());
        assertEquals("SMS", template.getType());
        assertEquals("New Subject", template.getSubject());
    }

    @Test
    public void testUpdateTemplate(){
        NotificationTemplate template = new NotificationTemplate(1, "Old Content", "Email", "Old Subject", "2024-06-18");
        template.updateTemplate("Updated Content", "SMS", "Updated Subject");

        assertEquals("Updated Content", template.getContent());
        assertEquals("SMS", template.getType());
        assertEquals("Updated Subject", template.getSubject());
    }

    @Test
    public void testDeleteTemplate(){
        NotificationTemplate template = new NotificationTemplate(2, "Content", "Email", "Subject", "2024-06-18");
        template.createTemplate("Delete Test", "Email", "Delete", "2024-06-18");

        NotificationTemplate.deleteTemplate(2);

        assertNull(NotificationTemplate.getTemplate(2));
    }
}
