package Model;

public class Event {
    private int eventId;
    private String name;
    private String date;
    private String location;
    private float price;

    // Constructor
    public Event(int eventId, String name, String date, String location, float price) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.price = price;
    }

    // Getters and Setters
    public int getEventId() { return eventId; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public float getPrice() { return price; }

    public void updateEventDetails(String name, String date, String location, float price) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.price = price;
    }

    public String getEventDetails() {
        return "Event: " + name + ", Date: " + date + ", Location: " + location + ", Price: $" + price;
    }
}
