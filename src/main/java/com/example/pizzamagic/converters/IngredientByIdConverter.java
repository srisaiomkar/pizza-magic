package com.example.pizzamagic.converters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.pizzamagic.models.Ingredient;
import com.example.pizzamagic.models.Ingredient.Type;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter(){
        ingredientMap.put("MGBR", new Ingredient("MGBR", "Multigrain Bread", Type.BREAD));
        ingredientMap.put("WTBR", new Ingredient("WTBR", "Wheat Bread", Type.BREAD));
        ingredientMap.put("CPBR", new Ingredient("CPBR", "Chick Peas Bread", Type.BREAD));
        ingredientMap.put("3BCH", new Ingredient("3BCH", "Three Blend Cheese", Type.CHEESE));
        ingredientMap.put("MZCH", new Ingredient("MZCH", "Mozarella Cheese", Type.CHEESE));
        ingredientMap.put("XTCH", new Ingredient("XTCH", "Extra Cheese", Type.CHEESE));
        ingredientMap.put("SP", new Ingredient("SP", "Spinach", Type.TOPPINGS));
        ingredientMap.put("TM", new Ingredient("TM", "Tomatoes", Type.TOPPINGS));
        ingredientMap.put("ON", new Ingredient("ON", "Onion", Type.TOPPINGS));
        ingredientMap.put("MS", new Ingredient("MS", "Mushroom", Type.TOPPINGS));
    }

    @Override
    public Ingredient convert(String id){
        return ingredientMap.get(id);
    }
}
