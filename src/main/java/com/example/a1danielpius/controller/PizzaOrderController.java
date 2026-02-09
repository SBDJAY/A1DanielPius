//<!--AI WAS USED HERE TO PROVIDE FIX TO WHITELABEL-->
package com.example.a1danielpius.controller;
import com.example.a1danielpius.data.CrustType;
import com.example.a1danielpius.data.PizzaOrder;
import com.example.a1danielpius.data.PizzaSize;
import com.example.a1danielpius.data.Topping;
import com.example.a1danielpius.service.PizzaOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//Swapped out for diffrent approach for error handling
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/order")
public class PizzaOrderController {

    private final PizzaOrderService pizzaOrderService;

    public PizzaOrderController(PizzaOrderService pizzaOrderService) {
        this.pizzaOrderService = pizzaOrderService;
    }

    // These will ALWAYS be available in the model
    @ModelAttribute("sizes")
    public PizzaSize[] sizes() {
        return PizzaSize.values();
    }

    @ModelAttribute("crusts")
    public CrustType[] crusts() {
        return CrustType.values();
    }

    @ModelAttribute("toppings")
    public Topping[] toppings() {
        return Topping.values();
    }

    // Show form
    @GetMapping("/new")
    public String showOrders(Model model) {
        model.addAttribute("pizzaOrder", new PizzaOrder()); // FIXED
        return "orderform";
    }

    // Submit form
    @PostMapping("/new")
    public String submitOrders(@ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder, Model model) {

        // Customer Name field handler
        if (pizzaOrder.getCustomerName() == null || pizzaOrder.getCustomerName().trim().isEmpty()) {
            model.addAttribute("error", "Customer name is required");
            return "orderform";
        }

        // Pizza Quantity handler
        if (pizzaOrder.getQuantity() < 1 || pizzaOrder.getQuantity() > 10) {
            model.addAttribute("error", "Quantity must be between 1 and 10");
            return "orderform";
        }

        // Delivery Address handler
        if (pizzaOrder.isDelivery() &&
                (pizzaOrder.getDeliveryAddress() == null ||
                        pizzaOrder.getDeliveryAddress().trim().isEmpty())) {

            model.addAttribute("error", "Delivery address is required for delivery orders");
            return "orderform";
        }

        pizzaOrderService.addOrder(pizzaOrder);
        model.addAttribute("order", pizzaOrder);

        return "ordersummary";
    }

    // Order history mapping
    @GetMapping("/history")
    public String showOrderHistory(Model model) {
        model.addAttribute("orders", pizzaOrderService.getAllPizzaOrders());
        return "orderhistory";
    }
}

