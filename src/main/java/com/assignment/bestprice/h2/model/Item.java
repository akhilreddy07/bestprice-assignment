package com.assignment.bestprice.h2.model;

import java.util.Objects;

public class Item {
    private String id;
    private Float cost;
    private ItemType type;

    private int quantity;

    public Item() {

    }

    public Item(String id, Float cost, ItemType type, int quantity) {
        this.id = id;
        this.cost = cost;
        this.type = type;
        this.quantity = quantity;
    }

    public void setId(String id) {this.id = id;}

    public String getId() {return id;}

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getCost() {
        return cost;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, type, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Item item = (Item) obj;
        // field comparison
        return Objects.equals(id, item.id)
                && Objects.equals(cost, item.cost)
                && Objects.equals(type, item.type);
    }
}