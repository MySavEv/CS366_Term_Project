package com.cs366.restaurant.controller;

import com.cs366.restaurant.model.Menu;
import com.cs366.restaurant.model.OrderDetail;
import com.cs366.restaurant.model.OrderItem;
import com.cs366.restaurant.repository.MenuRepository;
import com.cs366.restaurant.repository.OrderDetailRepository;
import com.cs366.restaurant.service.KafkaProducerService;
import com.cs366.restaurant.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    
    private final KafkaProducerService kafkaProducer;

    @PostMapping("/send-order")
    public ResponseEntity<String> sendMockOrder() {
        kafkaProducer.sendMockOrder();
        return ResponseEntity.ok("✅ Mock order sent");
    }

    @GetMapping("/menus")
    public ResponseEntity<?> getMenus() {
        List<Menu> menus = menuRepository.findAll();

        return ResponseHandler.generateResponse("Menu Lists", true, menus);
    }
    
    @PostMapping("/menu")
    public ResponseEntity<?> addMenu(@RequestBody Menu menu) {
        return ResponseHandler.generateResponse("Menu Item added successfully", true, menuRepository.save(menu));
    }

    @PutMapping("/menu/{menuItemId}")
    public ResponseEntity<?> updateMenu(@PathVariable String menuId, @RequestBody Menu updatedMenu) {
        updatedMenu.setMenuId(Integer.parseInt(menuId));
        return ResponseHandler.generateResponse("Menu Item Updated successfully", true, null);
    }
}
