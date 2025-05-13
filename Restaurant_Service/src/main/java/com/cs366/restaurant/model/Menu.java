package com.cs366.restaurant.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "menuId", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long menuId;
    private String name;
    private int price;
    private String description;
    private boolean available;

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

}
