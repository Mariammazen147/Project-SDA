package com.example.demo.services;

import com.example.demo.models.Event;
import java.util.List;

public interface EventService {
    void addEvent(Event event);
    Event getEventById(int eventId);
    List<Event> getAllEvents();
    void updateEvent(Event event);
    void deleteEvent(int eventId);
}
