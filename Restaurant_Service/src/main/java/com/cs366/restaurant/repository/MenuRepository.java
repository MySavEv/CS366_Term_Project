
package com.cs366.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs366.restaurant.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuId(String menuId);
}
