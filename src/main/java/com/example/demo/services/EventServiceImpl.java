package com.example.demo.services;

import com.example.demo.dao.MockApiEventDAO;
import com.example.demo.models.Event;
import com.example.demo.models.Notification;
import com.example.demo.dao.NotificationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private MockApiEventDAO eventDAO; // Updated to use Mock API

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public String addEvent(Event event) {
        eventDAO.saveEvent(event);
        Notification notification = new Notification();
        notification.setConcernedId(event.getEventId());
        notification.setMessage("Event (ID: " + event.getEventId() + ") has been successfully created.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
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
    public String updateEvent(Event event) {
        eventDAO.updateEvent(event);
        Notification notification = new Notification();
        notification.setConcernedId(event.getEventId());
        notification.setMessage("Event (ID: " + event.getEventId() + ") has been successfully updated.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }

    @Override
    public String deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
        Notification notification = new Notification();
        notification.setConcernedId(eventId);
        notification.setMessage("Event (ID: " + eventId + ") has been successfully deleted.");
        notificationDAO.saveNotification(notification);
        return notification.getMessage();
    }
}
