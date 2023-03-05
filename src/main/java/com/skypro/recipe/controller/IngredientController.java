package com.skypro.recipe.controller;

import com.skypro.recipe.model.Ingredient;
import com.skypro.recipe.model.Recipe;
import com.skypro.recipe.service.IngredientService;
import com.skypro.recipe.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable long id){
        return ResponseEntity.of(ingredientService.getById(id));
    }
}
