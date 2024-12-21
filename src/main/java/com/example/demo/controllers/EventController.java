package com.example.demo.controllers;

import com.example.demo.models.Event;
import com.example.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public String addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
        return "Event added successfully!";
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable("id") int id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PutMapping("/{id}")
    public String updateEvent(@PathVariable("id") int id, @RequestBody Event event) {
        event.setEventId(id);
        eventService.updateEvent(event);
        return "Event updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable("id") int id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully!";
    }
}
