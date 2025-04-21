package com.cs366.order.controller;

import com.cs366.order.model.Order;
import com.cs366.order.dto.OrderRequest;
import com.cs366.order.model.OrderItem;
import com.cs366.order.repository.OrderRepository;
import com.cs366.order.service.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderProducer producer;
    private final OrderRepository orderRepository;

    public OrderController(OrderProducer producer, OrderRepository orderRepository) {
        this.producer = producer;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequest request) {
        System.out.println("🟢 /order/create");

        if (request.getUser_id() == null || request.getUser_id().isEmpty() || request.getItems().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "User not found or items missing"));
        }

        Order order = new Order();
        order.setUserId(request.getUser_id());
        order.setAddress(request.getAddress());
        order.setNote(request.getNote());
        order.setStatus("Preparing");
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        for (OrderRequest.Item reqItem : request.getItems()) {
            OrderItem item = new OrderItem();
            item.setMenuId(reqItem.getMenu_id());
            item.setQuantity(reqItem.getQuantity());
            item.setOrder(order); 
            items.add(item);
        }
        order.setItems(items);
        
        Order savedOrder = orderRepository.save(order);

        producer.sendOrderCreated(savedOrder);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Order created",
                "order_id", savedOrder.getOrderId()));
    }

    @GetMapping("/status/{order_id}")
public ResponseEntity<Map<String, Object>> getOrderStatus(@PathVariable("order_id") Long orderId) {
    Optional<Order> orderOpt = orderRepository.findById(orderId);

    if (orderOpt.isEmpty()) {
        return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", "Order not found"));
    }

    Order order = orderOpt.get();

    return ResponseEntity.ok(Map.of(
            "status", "success",
            "order_id", order.getOrderId(),
            "current_status", order.getStatus()));
}

    @PutMapping("/cancel/{order_id}")
    public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable("order_id") Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);

        if (orderOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Order not found"));
        }

        Order order = orderOpt.get();

        if (!order.getStatus().equalsIgnoreCase("Preparing")) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Order cannot be cancelled at this stage"));
        }

        order.setStatus("Cancelled");
        orderRepository.save(order);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Order cancelled"));
    }

    @GetMapping("/history/{user_id}")
    public ResponseEntity<?> getOrderHistory(@PathVariable("user_id") String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
    
        if (orders.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "No order history found"));
        }
    
        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Order order : orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("order_id", order.getOrderId());

        List<Map<String, Object>> menuList = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("menu_id", item.getMenuId());
            itemMap.put("quantity", item.getQuantity());
            menuList.add(itemMap);
        }

        orderMap.put("menu", menuList);
        orderMap.put("status", order.getStatus());
        orderMap.put("created_at", order.getCreatedAt());
        responseList.add(orderMap);
        }

        return ResponseEntity.ok(responseList);
    }
}
