package com.cs366.rider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs366.rider.service.RiderService;

@RestController
@RequestMapping("/riders")
public class RiderCommandController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/{orderId}/pickup")
    public String pickedUp(@PathVariable String orderId, @RequestParam String riderId) {
        riderService.riderPickedUp(orderId, riderId);
        return "✅ Rider picked up food.";
    }

    @PostMapping("/{orderId}/deliver")
    public String delivered(@PathVariable String orderId, @RequestParam String riderId) {
        riderService.riderDelivered(orderId, riderId);
        return "✅ Rider delivered food.";
    }
}