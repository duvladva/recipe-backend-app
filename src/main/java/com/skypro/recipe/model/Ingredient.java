package com.skypro.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;



/**
 * Ингредиент
 */

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int count;
    private String measureUnit;
}


