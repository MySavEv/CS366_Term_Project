package com.cs366.notification.event;

import lombok.Data;

@Data
public class UserEventPayload {
    private String eventType;
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private String eventTime;
}