package com.example.demo.services;

import com.example.demo.dao.EventDAO;
import com.example.demo.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Override
    public void addEvent(Event event) {
        eventDAO.saveEvent(event);
    }

    @Override
    public Event getEventById(int eventId) {
        return eventDAO.getEventById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    @Override
    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
    }

    @Override
    public void deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
    }
}
