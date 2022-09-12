package com.assignment.bestprice.service;

import com.assignment.bestprice.h2.model.Discount;
import com.assignment.bestprice.h2.model.Item;
import com.assignment.bestprice.h2.model.ItemType;

import java.util.List;
import java.util.Map;

public interface DiscountService {

    Double getBestPrice(List<Item> items);
    Map<ItemType, Discount> getDiscounts();

    void addDiscount(ItemType it, Discount d);

    void removeDiscount(String discountCode);
}
