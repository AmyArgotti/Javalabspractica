package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter@ToString
public class EventUser {
    private String userId;
    private String eventId;

    public EventUser(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
