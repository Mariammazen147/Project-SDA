package com.example.demo.dao;

import com.example.demo.models.Event;
import java.util.List;

public interface EventDAO {
    void saveEvent(Event event);
    Event getEventById(int eventId);
    List<Event> getAllEvents();
    void updateEvent(Event event);
    void deleteEvent(int eventId);
    List<Event> getEventsByLocation(String location);
}
