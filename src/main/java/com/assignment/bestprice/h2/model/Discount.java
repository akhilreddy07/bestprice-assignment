package com.assignment.bestprice.h2.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Map;

public class Discount {

    private String discountCode;
    private Float percentage;
    private ItemType itemType;
    private Map<String, Map<String, String>> extraInfo;

    public Discount(String discountCode, Float percentage, ItemType itemType, Map<String, Map<String, String>> extraInfo) {
        this.discountCode = discountCode;
        this.percentage = percentage;
        this.itemType = itemType;
        this.extraInfo = extraInfo;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setExtraInfo(Map<String, Map<String, String>> extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Map<String, Map<String, String>> getExtraInfo() {
        return extraInfo;
    }
}