package com.cs366.restaurant.controller;

import com.cs366.restaurant.model.Menu;
import com.cs366.restaurant.repository.MenuRepository;
import com.cs366.restaurant.utils.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

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
