package com.assignment.bestprice.h2.model;

import java.util.Objects;

public class ItemType {
    private String type;

    public ItemType() {}
    public ItemType(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return  type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
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

        ItemType itemType = (ItemType) obj;
        return Objects.equals(this.type, itemType.type);
    }

    public static ItemType getClothesType() {
        ItemType clothes = new ItemType("CLOTHES");
        return  clothes;
    }

    public static ItemType getALLType() {
        ItemType clothes = new ItemType("ALL");
        return  clothes;
    }

    public static ItemType getElectronicsType() {
        ItemType clothes = new ItemType("ELECTRONICS");
        return  clothes;
    }

    @Override
    public String toString() {
        return type;
    }
}