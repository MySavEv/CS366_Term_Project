package com.cs366.rider.dto;

public class RiderPickedUpFoodEvent {
    private String orderId;
    private String riderId;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getRiderId() {
        return riderId;
    }
    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

}