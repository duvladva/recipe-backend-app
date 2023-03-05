package com.skypro.recipe.service;

import com.skypro.recipe.model.Ingredient;

import java.util.Optional;

/**
 * Сервис для работы с ингредиентами
 */

public interface IngredientService {
    /**
     * Сохранить ингредиент
     * @param ingredient ингредиент для сохранения
     * @return сохраненный ингредиент
     */
    Ingredient save (Ingredient ingredient);

    /**
     * получение ингредиента по id
     * @param id идентификатор ингредиента
     * @return ингредиент
     */

    Optional<Ingredient> getById(Long id);

}
