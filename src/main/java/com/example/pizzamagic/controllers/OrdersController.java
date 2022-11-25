package com.example.pizzamagic.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pizzamagic.models.PizzaOrder;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrdersController {

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("pizzaOrder", new PizzaOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid PizzaOrder pizzaOrder, Errors errors){
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("pizza order: "+ pizzaOrder);
        return "redirect:/";
    }
}
