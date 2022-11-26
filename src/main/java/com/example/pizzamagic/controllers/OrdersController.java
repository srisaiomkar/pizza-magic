package com.example.pizzamagic.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.pizzamagic.models.PizzaOrder;
import com.example.pizzamagic.repositories.interfaces.IOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orders")
@SessionAttributes("pizzaOrder")
@Slf4j
public class OrdersController {

    private IOrderRepository orderRepository;

    public OrdersController(IOrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid PizzaOrder pizzaOrder, Errors errors){
        if(errors.hasErrors()){
            return "orderForm";
        }
        orderRepository.save(pizzaOrder);
        log.info("pizza order: "+ pizzaOrder);
        return "redirect:/";
    }
}
