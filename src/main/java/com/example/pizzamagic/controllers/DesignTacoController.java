package com.example.pizzamagic.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pizzamagic.models.Ingredient;
import com.example.pizzamagic.models.Pizza;
import com.example.pizzamagic.models.Ingredient.Type;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/design")
@Slf4j
public class DesignTacoController {

    @ModelAttribute
    private void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("MGBR", "Multigrain Bread", Type.BREAD),
            new Ingredient("WTBR", "Wheat Bread", Type.BREAD),
            new Ingredient("CPBR", "Chick Peas Bread", Type.BREAD),
            new Ingredient("3BCH", "Three Blend Cheese", Type.CHEESE),
            new Ingredient("MZCH", "Mozarella Cheese", Type.CHEESE),
            new Ingredient("XTCH", "Extra Cheese", Type.CHEESE),
            new Ingredient("SP", "Spinach", Type.TOPPINGS),
            new Ingredient("TM", "Tomatoes", Type.TOPPINGS),
            new Ingredient("ON", "Onion", Type.TOPPINGS),
            new Ingredient("MS", "Mushroom", Type.TOPPINGS)
        );

        Type[] types = Type.values(); 
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }


    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("pizza", new Pizza());
        return "design";
    }

    // @Valid indicates apply validations 
    @PostMapping
    public String processPizza(@Valid Pizza pizza, Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("pizza design" + pizza);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream()
            .filter(ingredient -> ingredient.getType().equals(type))
            .collect(Collectors.toList());
    }

}
