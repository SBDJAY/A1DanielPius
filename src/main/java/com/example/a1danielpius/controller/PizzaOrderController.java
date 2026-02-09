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

    //Made Orders mapping
    @GetMapping("/new")
    public String showOrders(Model model) {
        model.addAttribute("pizzaOrders", new PizzaOrder());
        model.addAttribute("sizes", PizzaSize.values());
        model.addAttribute("crusts", CrustType.values());
        model.addAttribute("toppings", Topping.values());

        return "order-form";
    }
    //Form Submission Mapping
    @PostMapping("/new")
    public String submitOrders(
             @ModelAttribute ("pizzaOrder") PizzaOrder pizzaOrder, Model model ) {
        //Customer Name field handler
        if (pizzaOrder.getCustomerName() == null || pizzaOrder.getCustomerName().trim().isEmpty()) {
            model.addAttribute("error", "Customer name is required");
            return reloadForm(model, pizzaOrder);
        }
        //Pizza Quantity handler
        if (pizzaOrder.getQuantity() < 1 || pizzaOrder.getQuantity() > 10) {
            model.addAttribute("error", "Quantity must be between 1 and 10");
            return reloadForm(model, pizzaOrder);
        }
        //Deliverry Field empty handler
        if (pizzaOrder.isDelivery() &&
                (pizzaOrder.getDeliveryAddress() == null ||
                        pizzaOrder.getDeliveryAddress().trim().isEmpty())) {

            model.addAttribute("error", "Delivery address is required for delivery orders");
            return reloadForm(model, pizzaOrder);
        }

        pizzaOrderService.addOrder(pizzaOrder);
        model.addAttribute("order", pizzaOrder);
        return "order-summary";
    }
    //order history mapping
    @GetMapping("/history")
    public String showOrderHistory(Model model) {
        model.addAttribute("orders", pizzaOrderService.getAllPizzaOrders());
        return "order-history";
    }

    //This is to help with error handiling
    private String reloadForm(Model model, PizzaOrder pizzaOrder) {
        model.addAttribute("pizzaOrder", pizzaOrder);
        model.addAttribute("sizes", PizzaSize.values());
        model.addAttribute("crusts", CrustType.values());
        model.addAttribute("toppings", Topping.values());
        return "order-form";
    }
}

