package com.skypro.recipe.service;

import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.model.Recipe;

/**
 * Сервис валидации
 */
public interface ValidationService {
    /**
     * Валидация рецепта
     * @param recipe рецепт для валидации
     * @return true - рецепт корректный
     */
    public boolean validate(Recipe recipe);

    /**
     * Валидация ингредиента
     * @param ingredient ингредиент для валидации
     * @return true - ингредиент корректный
     */
    public boolean validate(Ingredient ingredient);

}
