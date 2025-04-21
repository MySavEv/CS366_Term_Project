package com.cs366.restaurant.dto;

import lombok.Data;

@Data
public class OrderStatusUpdate {
    private String orderId;
    private String status;
}
