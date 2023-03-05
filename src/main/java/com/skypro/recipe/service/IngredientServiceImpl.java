package com.skypro.recipe.service;

import com.skypro.recipe.exeption.ValidationExeption;
import com.skypro.recipe.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static long idCounter = 1;
    private Map<Long, Ingredient> ingredients = new HashMap<>();
    private final ValidationService validationService;

    public IngredientServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }


    @Override
    public Ingredient save(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationExeption(ingredient.toString());

        }
        return ingredients.put(idCounter++, ingredient);
    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return Optional.ofNullable(ingredients.get(id));
    }
}
