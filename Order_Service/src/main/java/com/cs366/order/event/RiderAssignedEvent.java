package com.cs366.order.event;

public class RiderAssignedEvent {
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
