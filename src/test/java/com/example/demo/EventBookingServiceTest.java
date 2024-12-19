package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventBookingServiceTest {

    @Test
    public void testFetchEvents() {
        EventAPIIntegration api = Mockito.mock(EventAPIIntegration.class);
        EventBookingService service = new EventBookingService(api);
        List<Event> events = Arrays.asList(new Event(1, "Concert", "2024-12-25", "Cairo", 100.0f));
        when(api.getAvailableEvents("Cairo", "2024-12-25")).thenReturn(events);

        List<Event> fetchedEvents = service.fetchEvents("Cairo", "2024-12-25");
        assertEquals(1, fetchedEvents.size());
        assertEquals("Concert", fetchedEvents.get(0).getName());
    }

    @Test
    public void testBookEvent() {
        EventAPIIntegration api = Mockito.mock(EventAPIIntegration.class);
        EventBookingService service = new EventBookingService(api);
        when(api.bookEvent(1, 123)).thenReturn(true);

        boolean result = service.bookEvent(1, 123);
        assertTrue(result);
    }
}