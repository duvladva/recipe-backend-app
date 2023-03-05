package com.skypro.recipe.service;


import com.skypro.recipe.model.Recipe;

import java.util.Optional;

/**
 * Сервис по работе с рецептами
 */

public interface RecipeService {

         /**
         * Сохранить рецепт
         * @param recipe рецепт для сохранения
         * @return сохраненный ингредиент
         */
        Recipe save (Recipe recipe);

        /**
         * получение рецепта по id
         * @param id идентификатор рецепта
         * @return рецепт
         */

        Optional<Recipe> getById(Long id); // Главная цель Optional — замена null-значений, благодаря этому должна
                                            // повышаться безопасность и читаемость кода
}