package com.skypro.recipe.service;


import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
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

    /**
     * Обновление рецепта
     * @param id идентификатор
     * @param recipe рецепт
     * @return обновленный рецепт
     */
    Recipe update(Long id, Recipe recipe);

    /**
     * Удаление рецепта
     * @param id идентификатор
     * @return удаленный рецепт
     */
    Recipe delete(Long id);

    /**
     *
     * @return мапа рецептов
     */
    Map<Long, Recipe> getAll();

    /**
     * Чтение файла рецептов
     * @Return файл рецептов
     */
    File readFile();

    /**
     * Загрузка файла рецептов
     * @param file файл рецептов
     * @throws IOException
     */
    void  uploadFile(MultipartFile file) throws IOException;

    File prepareRecipesTxt() throws IOException;

}
