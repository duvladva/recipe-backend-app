package com.skypro.recipe.exeption;

/**
 * Ошибка валидации
 */
public class ValidationExeption extends RuntimeException{
    public ValidationExeption(String entity) {
        super("Ошибка валидации сущности: " + entity);
    }
}
