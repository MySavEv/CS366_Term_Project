package com.cs366.rider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs366.rider.dto.RiderAssignedEvent;
import com.cs366.rider.dto.RiderDeliveredFoodEvent;
import com.cs366.rider.dto.RiderPickedUpFoodEvent;

@Service
public class RiderService {

    @Autowired
    private RiderEventProducer eventProducer;

    // จำลองการหาไรเดอร์
    public void assignRider(String orderId) {
        String riderId = "RIDER-" + (int)(Math.random() * 1000);
        System.out.println("🛵 Assigned Rider " + riderId + " to Order " + orderId);

        // ส่ง Event
        RiderAssignedEvent event = new RiderAssignedEvent();
        event.setOrderId(orderId);
        event.setRiderId(riderId);

        eventProducer.sendRiderAssigned(event);
    }

    public void riderPickedUp(String orderId, String riderId) {
        RiderPickedUpFoodEvent event = new RiderPickedUpFoodEvent();
        event.setOrderId(orderId);
        event.setRiderId(riderId);
        eventProducer.sendPickedUp(event);
    }

    public void riderDelivered(String orderId, String riderId) {
        RiderDeliveredFoodEvent event = new RiderDeliveredFoodEvent();
        event.setOrderId(orderId);
        event.setOrderId(riderId);
        eventProducer.sendDelivered(event);
    }
}