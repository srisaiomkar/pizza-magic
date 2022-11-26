package com.example.pizzamagic.repositories.interfaces;

import com.example.pizzamagic.models.PizzaOrder;

public interface IOrderRepository {
    PizzaOrder save(PizzaOrder pizzaOrder);
}
