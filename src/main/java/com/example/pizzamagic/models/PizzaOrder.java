package com.example.pizzamagic.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class PizzaOrder {

    @NotBlank(message = "Name cannot be empty")
    private String deliverToName;

    @NotBlank(message = "Street cannot be empty")
    private String deliveryStreet;

    @NotBlank(message = "delivery city cannot be empty")
    private String deliveryCity;

    @NotBlank(message = "delivery state cannot be empty")
    private String deliveryState;

    @NotBlank(message = "zip code cannot be empty")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "(0[1-9]|1[0-2])/20[0-9]{2}$",message = "expiration date should be in MM/YYYY format")
    private String ccExpiration;
    
    @Digits(integer = 3,fraction = 0,message = "Invalid CVV")
    private String ccCVV;
    
    private List<Pizza> pizzas = new ArrayList<>(); 

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }
}
