package com.example.pizzamagic.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Pizza {

    private Long id;

    @NotBlank
    @Size(min = 3,message = "Size cannot be smaller than 3")
    private String name;

    @NotNull
    @Size(min = 1,message = "There should be atleast 1 ingredient")
    private List<Ingredient> ingredients;

    private Date createdAt = new Date();
}
