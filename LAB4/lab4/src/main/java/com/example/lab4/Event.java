package com.example.lab4;

import java.time.LocalDate;

public class Event {
    private String eventName;
    private LocalDate eventDate;

    public Event(String eventName, LocalDate eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }
}