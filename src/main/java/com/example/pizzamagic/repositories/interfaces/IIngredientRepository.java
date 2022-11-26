package com.example.pizzamagic.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.pizzamagic.models.Ingredient;

public interface IIngredientRepository {
    List<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
