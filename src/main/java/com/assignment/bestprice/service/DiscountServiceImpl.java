package com.assignment.bestprice.service;

import com.assignment.bestprice.h2.model.Discount;
import com.assignment.bestprice.h2.model.Item;
import com.assignment.bestprice.h2.model.ItemType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService, ApplicationRunner {
    private List<Discount> discounts = new ArrayList<>();
    private static Map<ItemType, Discount> discountMap = new HashMap<>();
    static Function<List<Item>, Double> f = f -> f.stream().mapToDouble(Item::getCost).reduce(0, (a, b) -> a + b);

    static Double calculate(Double finalPrice, Float percentage) {
        Double totalPriceDiscounted = finalPrice * ((100 - percentage) / 100);
        return totalPriceDiscounted;
    }

    @Override
    public Double getBestPrice(List<Item> items) {

        Map<ItemType, List<Item>> itemsByType = items.stream().collect(Collectors.groupingBy(Item::getType));
        if (itemsByType.isEmpty()) {
            return items.stream().mapToDouble(b -> b.getCost()).reduce(0, Double::sum);
        }
        Discount discount = discountMap.get(ItemType.getALLType());
        Double totalPriceDiscounted = Double.MAX_VALUE;
        if (canApplyAllDiscount(items, discount)) {
            Double totalPrice = f.apply(items);
            totalPriceDiscounted = calculate(totalPrice, discount.getPercentage());
        }

        List<Double> disCountedPriceList = new ArrayList<>();
        disCountedPriceList.add(totalPriceDiscounted);
        Double discountedPricePerItemsInCart = Double.valueOf(0);
        for (Map.Entry<ItemType, List<Item>> entry : itemsByType.entrySet()) {
            Discount d = discountMap.get(entry.getKey());
            discountedPricePerItemsInCart += applyDiscount(entry.getValue(), d);
        }
        return Math.min(discountedPricePerItemsInCart, totalPriceDiscounted);
    }

    static boolean canApplyDiscount(List<Item> items, Discount discount) {
        switch (discount.getItemType().getType()) {
            case "ALL":
                return canApplyAllDiscount(items, discount);
        }
        return false;
    }

    static boolean canApplyAllDiscount(List<Item> items, Discount discount) {
        Map<String, Map<String, String>> res = discount.getExtraInfo();
        Map<String, String> res1 = res.get("TOTAL_ITEMS_COST");
        Integer i = Integer.parseInt(res1.get("ge_eq_price"));
        Double price = f.apply(items);
        return price > i;
    }


    static Double applyDiscount(List<Item> items, Discount discount) {
        Map<String, Map<String, String>> res = discount != null?discount.getExtraInfo():null;
        Float discountPercentage = discount != null?discount.getPercentage(): 0;

        Double discountedPrice = Double.valueOf(0);
        for (Item i : items) {
            if (res != null) {
                Map<String, String> disPerItemCode = res.get(i.getId());
                if (disPerItemCode != null) {
                    int quantityForDiscount = Integer.parseInt(disPerItemCode.get("ge_eq_quantity"));
                    if (i.getQuantity() >= quantityForDiscount) {
                        discountedPrice += calculate(new Double(i.getQuantity() * i.getCost()), Float.parseFloat(disPerItemCode.get("percentage")));
                    } else {
                        Float totalPrice = i.getQuantity() * i.getCost();
                        discountedPrice += calculate(Double.valueOf(totalPrice), discountPercentage);
                    }
                } else {
                    Float totalPrice = i.getQuantity() * i.getCost();
                    discountedPrice += calculate(Double.valueOf(totalPrice), discountPercentage);
                }
            } else {
                Float totalPrice = i.getQuantity() * i.getCost();
                discountedPrice += calculate(Double.valueOf(totalPrice), discountPercentage);
            }
        }
        return discountedPrice;
    }

    @Override
    public Map<ItemType, Discount> getDiscounts() {
        return discountMap;
    }

    @Override
    public void addDiscount(ItemType it, Discount d) {
        discountMap.put(it, d);
    }

    @Override
    public void removeDiscount(String discountCode) {
        discountMap.entrySet().removeIf(entry -> entry.getValue().getDiscountCode().equals(discountCode));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Map<String, String>> extraInfo = new HashMap<>();
        Map<String, String> clothing123Discount = new HashMap<>();
        clothing123Discount.put("ge_eq_quantity", "2");
        clothing123Discount.put("percentage", "20");
        extraInfo.put("123", clothing123Discount);

        Map<String, Map<String, String>> extraAllInfo = new HashMap<>();
        Map<String, String> allDiscount = new HashMap<>();
        allDiscount.put("ge_eq_price", "100");
        extraAllInfo.put("TOTAL_ITEMS_COST", allDiscount);

        Discount abcDiscount = new Discount("ABC", 10F, ItemType.getClothesType(), extraInfo);
        Discount cdeDiscount = new Discount("CDE", 15F, ItemType.getALLType(), extraAllInfo);
        Discount ijkDiscount = new Discount("IJK", 5F, ItemType.getElectronicsType(), null);

        discountMap.put(ItemType.getClothesType(), abcDiscount);
        discountMap.put(ItemType.getALLType(), cdeDiscount);
        discountMap.put(ItemType.getElectronicsType(), ijkDiscount);
    }


}
