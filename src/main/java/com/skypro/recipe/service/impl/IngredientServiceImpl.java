package com.skypro.recipe.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.recipe.exeption.ValidationExeption;
import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.model.Recipe;
import com.skypro.recipe.service.IngredientService;
import com.skypro.recipe.service.ValidationService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private static long idCounter = 1;
    private Map<Long, Ingredient> ingredients = new HashMap<>();
    private final ValidationService validationService;
    private final FileService fileService;

    @Value("${path.to.ingredients.file}")
    private String ingredientsFilePath;

    @Value("${name.of.ingredients.files}")
    private String ingredientsFileName;
    private Path ingredientPath;


    @Override
    public Ingredient save(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationExeption(ingredient.toString());
        }
        ingredients.put(idCounter++, ingredient);
        fileService.saveMapToFile(ingredients, ingredientPath);

        return ingredient;
    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return Optional.ofNullable(ingredients.get(id));
    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationExeption(ingredient.toString());
        }

        ingredients.replace(id, ingredient);
        fileService.saveMapToFile(ingredients, ingredientPath);

        return ingredient;
    }

    @Override
    public Ingredient delete(Long id) {
        Ingredient ingredient = ingredients.remove(id);
        fileService.saveMapToFile(ingredients, ingredientPath);
        return ingredient;
    }

    @Override
    public Map<Long, Ingredient> getAll() {
        return ingredients;
    }

    @Override
    public File readFile() {
        return ingredientPath.toFile();
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        fileService.uploadFile(file, ingredientPath);
        ingredients = fileService.readMapFromFile(ingredientPath, new TypeReference<Map<Long, Ingredient>>() {
        });

    }

    @PostConstruct
    private void init() {
        ingredientPath = Path.of(ingredientsFilePath, ingredientsFileName);
        ingredients = fileService.readMapFromFile(ingredientPath, new TypeReference<Map<Long, Ingredient>>() {
        });
    }

}
