package com.example.kafka;

public class Item {
    private int id;
    private String name;
    private String category;

    public Item(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    @Override
    public String toString() {
    	return "Item detail: id="+id+", name=" + name + ", category=" + category;
    }
}
