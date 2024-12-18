package Service;
import Model.Event;
import API.EventAPIIntegration;
import java.util.List;

public class EventBookingService {
    private EventAPIIntegration eventAPI;

    public EventBookingService(EventAPIIntegration eventAPI) {
        this.eventAPI = eventAPI;
    }

    public List<Event> fetchEvents(String location, String date) {
        return eventAPI.getAvailableEvents(location, date);
    }

    public boolean bookEvent(int eventId, int userId) {
        return eventAPI.bookEvent(eventId, userId);
    }

    public void displayEvents(List<Event> events) {
        for (Event event : events) {
            System.out.println(event.getEventDetails());
        }
    }
}
