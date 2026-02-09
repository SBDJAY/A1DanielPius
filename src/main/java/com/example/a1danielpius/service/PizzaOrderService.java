package com.example.a1danielpius.service;

//This class wil provide the pricing discount and calculation logic

import com.example.a1danielpius.data.PizzaOrder;
import com.example.a1danielpius.data.PizzaSize;
import com.example.a1danielpius.data.Topping;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//Service Class
@Service
public class PizzaOrderService {
    private final List<PizzaOrder> pizzaOrders = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    //Pricing information
    private static final double SMALL_PRICE = 8.00;
    private static final double MEDIUM_PRICE = 10.00;
    private static final double LARGE_PRICE = 12.00;

    private static final double TOPPING_PRICE = 1.25;
    private static final double DELIVERY_FEE = 3.99;
    private static final double DISCOUNT_RATE = 0.10;
    private static final double TAX_RATE = 0.13;

    //Add Order Method
    public void  addOrder(PizzaOrder pizzaOrder) {
        //Change made here to make id long instead of integer to properly increment
        pizzaOrder.setId(idGenerator.getAndIncrement());
        calculatePrices(pizzaOrder);
        pizzaOrders.add(pizzaOrder);
    }

    public List<PizzaOrder> getAllPizzaOrders() {
        return pizzaOrders;
    }

    //Price Calculation
    private void calculatePrices(PizzaOrder pizzaOrder) {
        double basePrice = getBasePrice(pizzaOrder.getSize());
        int toppingCount = pizzaOrder.getToppings() == null ? 0 : pizzaOrder.getToppings().size();

        double pizzaSubtotal =
                (basePrice + (toppingCount * TOPPING_PRICE)) * pizzaOrder.getQuantity();

        double discount = 0.0;
        if (pizzaOrder.getQuantity() > 3) {
            discount = pizzaSubtotal * DISCOUNT_RATE;
        }

        double deliveryFee = pizzaOrder.isDelivery() ? DELIVERY_FEE : 0.0;

        double taxableAmount = pizzaSubtotal - discount + deliveryFee;
        double tax = taxableAmount * TAX_RATE;

        double total = taxableAmount + tax;

        pizzaOrder.setSubtotal(round(pizzaSubtotal));
        pizzaOrder.setDiscountAmount(round(discount));
        pizzaOrder.setTax(round(tax));
        pizzaOrder.setTotal(round(total));
    }

    //Method for getitng pizza price by size
    private double getBasePrice(PizzaSize size) {
        if (size == null) return 0.0;

        return switch (size) {
            case SMALL -> SMALL_PRICE;
            case MEDIUM -> MEDIUM_PRICE;
            case LARGE -> LARGE_PRICE;
        };
    }


    private double getToppingsCost(List<Topping> toppings) {
        if (toppings == null) return 0.0;
        return toppings.size() * TOPPING_PRICE;
    }

    //Logic For verifying if pizza order quantity is more then 3
    private double calculateDiscount(double pizzaSubtotal, int quantity) {
        if (quantity > 3) {
            return pizzaSubtotal * DISCOUNT_RATE;
        }
        return 0.0;
    }


    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
