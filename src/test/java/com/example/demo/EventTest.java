package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEventCreation() {
        Event event = new Event(1, "Concert", "2024-12-25", "Cairo", 100.0f);
        assertEquals(1, event.getEventId());
        assertEquals("Concert", event.getName());
        assertEquals("2024-12-25", event.getDate());
        assertEquals("Cairo", event.getLocation());
        assertEquals(100.0f, event.getPrice());
    }

    @Test
    public void testUpdateEventDetails() {
        Event event = new Event(1, "Concert", "2024-12-25", "Cairo", 100.0f);
        event.updateEventDetails("Updated Concert", "2024-12-26", "Alexandria", 150.0f);
        assertEquals("Updated Concert", event.getName());
        assertEquals("2024-12-26", event.getDate());
        assertEquals("Alexandria", event.getLocation());
        assertEquals(150.0f, event.getPrice());
    }

    @Test
    public void testGetEventDetails() {
        Event event = new Event(1, "Concert", "2024-12-25", "Cairo", 100.0f);
        String details = event.getEventDetails();
        assertEquals("Event: Concert, Date: 2024-12-25, Location: Cairo, Price: $100.0", details);
    }
}