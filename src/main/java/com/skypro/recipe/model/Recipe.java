package com.skypro.recipe.model;

import lombok.*;

import java.util.List;


/**
 * Рецепт
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private  int cookingTime;
    private List<Ingredient> ingredients; // список ингредиентов
    private List<String> steps; // список шагов

    @Override
    public String toString() {
        return name + "\n Время приготовления: " + cookingTime;
    }


}
