package com.assignment.bestprice.controller;

import com.assignment.bestprice.h2.model.Discount;
import com.assignment.bestprice.h2.model.ItemType;
import com.assignment.bestprice.model.BestPriceReq;
import com.assignment.bestprice.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {
    @Autowired
    private DiscountService discountService;


    @PostMapping("/items-in-cart")
    public ResponseEntity<Map<String, Double>> getBestPrice(@RequestBody BestPriceReq req) {
        Double discountedPrice = (discountService.getBestPrice(req.getItems()));
        Map<String, Double> resp = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.##");
        Double response  = Double.valueOf(df.format(discountedPrice));
        resp.put("priceAfterDiscount", response);
        return new ResponseEntity<Map<String, Double>>(resp, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<ItemType, Discount>> getDiscounts() {
        Map<ItemType, Discount> discountList = discountService.getDiscounts();
        return new ResponseEntity< Map<ItemType, Discount>>(discountList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<ItemType, Discount>> createDiscount(@RequestBody Discount discount) {
        discountService.addDiscount(discount.getItemType(), discount);
        return getDiscounts();
    }

    @DeleteMapping("/{discount_code}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable(value = "discount_code") String  discountCode) {
        discountService.removeDiscount(discountCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}