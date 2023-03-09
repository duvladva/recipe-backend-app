package com.skypro.recipe.service;

import com.skypro.recipe.model.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
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


    /**
     * Обновление ингредиента
     * @param id идентификатор
     * @param ingredient ингредиент
     * @return обновленный ингредиент
     */
    Ingredient update(Long id, Ingredient ingredient);

    /**
     * Удаление ингредиента
     * @param id идентификатор
     * @return удаленный ингредиент
     */
    Ingredient delete(Long id);

    /**
     *
     * @return мапа ингредиентов
     */
    Map<Long, Ingredient> getAll();

    /**
     * Чтение файла ингредиентов
     * @Return файл ингредиентов
     */
    File readFile();

    /**
     * Загрузка файла ингредиентов
     * @param file файл ингредиентов
     * @throws IOException
     */
    void  uploadFile(MultipartFile file) throws IOException;



}
