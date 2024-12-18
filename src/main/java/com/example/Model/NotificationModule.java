package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class NotificationModule{
    private Queue<Notification> notificationQueue;

    public NotificationModule(){
        notificationQueue=new LinkedList<>();
    }

    public void queueNotification(Notification notification){
        notificationQueue.add(notification);
    }

    public void sendNotification(Notification notification){
        notificationQueue.remove(notification);
        System.out.println("Sending Notification: "+notification.getMessage());
    }

    // Get Notification Status
    public String getNotificationStatus(int notificationId){
        for (Notification notification : notificationQueue){
            if (notification.getNotificationId()==notificationId){
                return "Notification is in the queue.";
            }
        }
        return "Notification not found in the queue.";
    }

    public NotificationTemplate loadNotificationTemplate(int templateId){
        return NotificationTemplate.getTemplate(templateId);
    }
}