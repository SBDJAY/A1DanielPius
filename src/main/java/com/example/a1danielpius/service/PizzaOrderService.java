package com.example.a1danielpius.service;

//This class wil provide the pricing discount and calculation logic

import com.example.a1danielpius.data.PizzaOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Service Class
@Service
public class PizzaOrderService {
    private final List<PizzaOrder> pizzaOrders = new ArrayList<>();
    private int newID = 1;

    //Pricing information
    private static final double SMALL_PRICE = 8.00;
    private static final double MEDIUM_PRICE = 10.00;
    private static final double LARGE_PRICE = 12.00;

    private static final double TOPPING_PRICE = 1.25;
    private static final double DELIVERY_FEE = 3.99;
    private static final double DISCOUNT_RATE = 0.10;
    private static final double TAX_RATE = 0.13;

}
