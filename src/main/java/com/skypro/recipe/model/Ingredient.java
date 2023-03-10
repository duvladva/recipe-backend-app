package com.skypro.recipe.model;

import lombok.*;


/**
 * Ингредиент
 */

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Ingredient {
    private String name;
    private int count;
    private String measureUnit;

    @Override
    public String toString() {
        return name + " - " + count + " " + measureUnit;
    }
}


