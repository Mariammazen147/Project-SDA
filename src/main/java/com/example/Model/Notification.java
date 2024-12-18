package org.example;

public class Notification{
    private int notificationId;
    private int bookingId;
    private String message;
    private String sentDate;

    public Notification(int notificationId,int bookingId,String message,String sentDate){
        this.notificationId=notificationId;
        this.bookingId=bookingId;
        this.message=message;
        this.sentDate=sentDate;
    }

    public void sendNotification(int bookingId,String message){
        this.bookingId=bookingId;
        this.message=message;
    }

    public String getNotificationDetails(){
        return String.format("Notification ID: %d, Booking ID: %d, Message: %s, Sent Date: %s",
                notificationId,bookingId,message,sentDate);
    }

    public int getNotificationId(){
        return notificationId;
    }

    public int getBookingId(){
        return bookingId;
    }

    public String getMessage(){
        return message;
    }
}
