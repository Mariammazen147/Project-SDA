package org.example;

import java.util.HashMap;
import java.util.Map;

public class NotificationTemplate{
    private int templateId;
    private String content;
    private String type;
    private String subject;
    private String createdDate;

    private static Map<Integer,NotificationTemplate> templateStore=new HashMap<>();

    public NotificationTemplate(int templateId,String content,String type,String subject,String createdDate){
        this.templateId=templateId;
        this.content=content;
        this.type=type;
        this.subject=subject;
        this.createdDate=createdDate;
    }

    public void createTemplate(String content,String type,String subject,String createdDate){
        this.content=content;
        this.type=type;
        this.subject=subject;
        this.createdDate=createdDate;
        templateStore.put(this.templateId, this);
    }

    public void updateTemplate(String content, String type, String subject){
        this.content=content;
        this.type=type;
        this.subject=subject;
    }

    public static NotificationTemplate getTemplate(int templateId){
        return templateStore.get(templateId);
    }

    public static void deleteTemplate(int templateId){
        templateStore.remove(templateId);
    }

    public int getTemplateId(){
        return templateId;
    }

    public String getContent(){
        return content;
    }

    public String getType(){
        return type;
    }

    public String getSubject(){
        return subject;
    }
}