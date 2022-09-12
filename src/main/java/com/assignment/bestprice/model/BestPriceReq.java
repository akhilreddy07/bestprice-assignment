package com.assignment.bestprice.model;

import com.assignment.bestprice.h2.model.Item;

import java.util.List;

public class BestPriceReq {

    private List<Item> items;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}