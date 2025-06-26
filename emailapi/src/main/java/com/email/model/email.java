package com.email.model;

public class email {
 
    private String to;
    
    private String subject;
    private String message;

    public String getto() {
        return to;
    }

    public void setFrom(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public email() {
    }

    public email(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public String toString() {
        return "email [from=" + to + ", message=" + message + ", subject=" + subject + "]";
    }

        
    
}
