package com.example.pizzamagic.models;

import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;
    
    public enum Type{
        CHEESE, TOPPINGS, BREAD
    }
}
