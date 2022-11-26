package com.example.pizzamagic.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.pizzamagic.models.Ingredient;
import com.example.pizzamagic.repositories.interfaces.IIngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IIngredientRepository ingredientRepository;

    public IngredientByIdConverter(IIngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id){
        return ingredientRepository.findById(id).orElse(null);
    }
}
