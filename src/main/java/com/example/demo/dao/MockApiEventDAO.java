package com.example.demo.dao;

import com.example.demo.models.Event;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class MockApiEventDAO implements EventDAO {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String mockApiUrl = "https://676bcd2dbc36a202bb85bf82.mockapi.io/api/events";

    @Override
    public void saveEvent(Event event) {
        restTemplate.postForObject(mockApiUrl, event, Event.class);
    }

    @Override
    public Event getEventById(int eventId) {
        String url = mockApiUrl + "/" + eventId;
        return restTemplate.getForObject(url, Event.class);
    }

    @Override
    public List<Event> getAllEvents() {
        Event[] events = restTemplate.getForObject(mockApiUrl, Event[].class);
        return Arrays.asList(events);
    }

    @Override
    public void updateEvent(Event event) {
        String url = mockApiUrl + "/" + event.getEventId();
        restTemplate.put(url, event);
    }

    @Override
    public void deleteEvent(int eventId) {
        String url = mockApiUrl + "/" + eventId;
        restTemplate.delete(url);
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        String url = mockApiUrl + "?location=" + location;
        Event[] events = restTemplate.getForObject(url, Event[].class);
        return Arrays.asList(events);
    }
}
