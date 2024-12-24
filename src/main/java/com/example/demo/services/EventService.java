package com.example.demo.services;

import com.example.demo.models.Event;
import java.util.List;

public interface EventService {
    String addEvent(Event event);
    Event getEventById(int eventId);
    List<Event> getAllEvents();
    String updateEvent(Event event);
    String deleteEvent(int eventId);
}
