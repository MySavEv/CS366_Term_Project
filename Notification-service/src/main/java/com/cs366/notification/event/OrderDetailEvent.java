package com.cs366.notification.event;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailEvent {
    private String orderId;
    private String restaurantId;
    private String customerName;
    private List<OrderedItem> items;
    private LocalDateTime orderedAt;
    private String note; // หมายเหตุจากลูกค้า (ถ้ามี)

    public static class OrderedItem {
        private String menuId;
        private String menuName;
        private int quantity;

        public OrderedItem() {}

        public OrderedItem(String menuId, String menuName, int quantity) {
            this.menuId = menuId;
            this.menuName = menuName;
            this.quantity = quantity;
        }

        public String getMenuId() { return menuId; }
        public void setMenuId(String menuId) { this.menuId = menuId; }

        public String getMenuName() { return menuName; }
        public void setMenuName(String menuName) { this.menuName = menuName; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    public OrderDetailEvent() {}

    public OrderDetailEvent(String orderId, String restaurantId, String customerName, List<OrderedItem> items, LocalDateTime orderedAt, String note) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.customerName = customerName;
        this.items = items;
        this.orderedAt = orderedAt;
        this.note = note;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public List<OrderedItem> getItems() { return items; }
    public void setItems(List<OrderedItem> items) { this.items = items; }

    public LocalDateTime getOrderedAt() { return orderedAt; }
    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
