package com.example.a1danielpius.data;


import java.time.LocalDateTime;
import java.util.List;

public class PizzaOrder {

    private int id;
    private String customerName;
    private int quantity;

    private boolean delivery;
    private String deliveryAddress;

    private double subTotal;
    private double discountAmmount;
    private double tax;
    private double total;

    //Time and set method
    private LocalDateTime orderTime;
    public PizzaOrder() {
        this.orderTime = LocalDateTime.now();
    }


    //All Data types that have a ENUM Type
    private PizzaSize size;
    private CrustType crust;
    private List<Topping> toppings;


    //Getters and Setters
    //ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //----------------------------------------------------
    //Customer Name
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    //----------------------------------------------------
    //Pizza Size
    public PizzaSize getSize() {
        return size;
    }
    public void setSize(PizzaSize size) {
        this.size = size;
    }
    //----------------------------------------------------
    //Crust
    public CrustType getCrust() {
        return crust;
    }
    public void setCrust(CrustType crust) {
        this.crust = crust;
    }
    //----------------------------------------------------
    //Toppings
    public List<Topping> getToppings() {
        return toppings;
    }
    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
    //----------------------------------------------------
    //Quantity
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //----------------------------------------------------
    //Delivery
    public boolean isDelivery() {
        return delivery;
    }
    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }
    //----------------------------------------------------
    //Delivery Address
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    //----------------------------------------------------
    //Sub Total
    public double getSubtotal() {
        return subTotal;
    }
    public void setSubtotal(double subtotal) {
        this.subTotal = subtotal;
    }
    //----------------------------------------------------
    //Discount ammount
    public double getDiscountAmount() {
        return discountAmmount;
    }
    public void setDiscountAmount(double discountAmount) {
        this.discountAmmount = discountAmount;
    }
    //----------------------------------------------------
    //Taxation
    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    //----------------------------------------------------
    //Total
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    //----------------------------------------------------
    //DateTime Function
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}

enum PizzaSize {
    SMALL,
    MEDIUM,
    LARGE
}

enum CrustType {
    THIN,
    REGULAR,
    THICK
}

enum Topping {
    PEPERONI,
    CHEESE,
    BEEF,
    BACON,
    MUSHROOMS,
    OLIVES
}