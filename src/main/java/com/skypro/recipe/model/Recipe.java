package com.skypro.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


/**
 * Рецепт
 */
@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private  int cookingTime;
    private List<Ingredient> ingredients; // список ингредиентов
    private List<String> steps; // список шагов

}
