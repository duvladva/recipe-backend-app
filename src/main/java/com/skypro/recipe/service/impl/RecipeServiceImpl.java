package com.skypro.recipe.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.recipe.exeption.ValidationExeption;
import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.model.Recipe;
import com.skypro.recipe.service.RecipeService;
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

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {


    private static long idCounter = 1;
    private Map<Long, Recipe> recipes = new HashMap<>();
    private final ValidationService validationService;

    private final FileService fileService;

    @Value("${path.to.recipes.file}")
    private String recipesFilePath;

    @Value("${name.of.recipes.files}")
    private String recipesFileName;

    @Value("${name.of.recipes.file}")
    private String recipesTxtFileName;

    private Path recipesPath;


    @Override
    public Recipe save(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationExeption(recipe.toString());

        }
        recipes.put(idCounter++, recipe);
        fileService.saveMapToFile(recipes, recipesPath);
        return recipe;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    @Override
    public Recipe update(Long id, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationExeption(recipe.toString());

        }
        recipes.replace(id, recipe);
        fileService.saveMapToFile(recipes, recipesPath);
        return recipe;
    }

    @Override
    public Recipe delete(Long id) {
        Recipe recipe = recipes.remove(id);
        fileService.saveMapToFile(recipes, recipesPath);

        return recipe;
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipes;
    }

    @Override
    public File readFile() {
        return recipesPath.toFile();
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        fileService.uploadFile(file, recipesPath);
        recipes = fileService.readMapFromFile(recipesPath, new TypeReference<Map<Long, Recipe>>() {
        });

    }

    @Override
    public File prepareRecipesTxt() throws IOException {
        return fileService
                .saveToFile(recipesToString(), Path.of(recipesFilePath, recipesFileName))
                .toFile();

    }

    @PostConstruct
    private void init() {
        recipesPath = Path.of(recipesFilePath, recipesFileName);
        recipes = fileService.readMapFromFile(recipesPath, new TypeReference<Map<Long, Recipe>>() {
        });
    }

    private String recipesToString() {
        StringBuilder sb = new StringBuilder();
        String listEl = " * ";

        for (Recipe recipe : recipes.values()) {
            sb.append(recipe.toString()).append("\n");

            sb.append("\nИнгредиенты:\n");
            for (Ingredient ingredient : recipe.getIngredients()) {
                sb.append(listEl).append(ingredient.toString()).append("\n");
            }

            sb.append("\nИнструкция приготовления:\n");
            for (String step : recipe.getSteps()) {
                sb.append(listEl).append(step).append("\n");
            }
        }
        return sb.append("/n").toString();
    }
}
