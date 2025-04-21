package com.cs366.customer.dto;

import lombok.Data;

@Data
public class UserEventPayload {
    private String eventType;
    private String userId;
    private String email;
    private String fullName;
    private String eventTime;
}