package com.example.pizzamagic.controllers;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.pizzamagic.models.Ingredient;
import com.example.pizzamagic.models.Pizza;
import com.example.pizzamagic.models.PizzaOrder;
import com.example.pizzamagic.models.Ingredient.Type;
import com.example.pizzamagic.repositories.interfaces.IIngredientRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
@Slf4j
public class DesignTacoController {

    private IIngredientRepository ingredientRepository;

    public DesignTacoController(IIngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    private void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Type.values(); 
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }


    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("pizzaOrder", new PizzaOrder());
        return "design";
    }

    // @Valid indicates apply validations 
    @PostMapping
    public String processPizza(
        @Valid Pizza pizza,
        @ModelAttribute PizzaOrder pizzaOrder,
        Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
        pizzaOrder.addPizza(pizza);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream()
            .filter(ingredient -> ingredient.getType().equals(type))
            .collect(Collectors.toList());
    }

}
