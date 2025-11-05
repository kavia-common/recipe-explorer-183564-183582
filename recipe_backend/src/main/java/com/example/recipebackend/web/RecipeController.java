package com.example.recipebackend.web;

import com.example.recipebackend.dto.RecipeDtos.RecipeRequest;
import com.example.recipebackend.dto.RecipeDtos.RecipeResponse;
import com.example.recipebackend.service.RecipeService;
import com.example.recipebackend.service.RecipeService.PagedResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 PUBLIC_INTERFACE
 REST controller for recipes: CRUD and search.
 */
@RestController
@RequestMapping("/api/recipes")
@Tag(name = "Recipes", description = "Recipe CRUD and search endpoints")
public class RecipeController {

    private final RecipeService recipes;

    public RecipeController(RecipeService recipes) {
        this.recipes = recipes;
    }

    @GetMapping
    @Operation(summary = "List recipes", description = "Returns paginated list of recipes")
    public ResponseEntity<PagedResult<RecipeResponse>> list(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(recipes.list(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recipe", description = "Returns a recipe by ID")
    public ResponseEntity<RecipeResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(recipes.get(id));
    }

    @PostMapping
    @Operation(summary = "Create recipe", description = "Creates a new recipe")
    public ResponseEntity<RecipeResponse> create(@Valid @RequestBody RecipeRequest req) {
        // For demo, assume authorId = 1
        return ResponseEntity.ok(recipes.create(req, 1L));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update recipe", description = "Updates an existing recipe by ID")
    public ResponseEntity<RecipeResponse> update(@PathVariable Long id, @Valid @RequestBody RecipeRequest req) {
        return ResponseEntity.ok(recipes.update(id, req));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recipe", description = "Deletes a recipe by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recipes.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search recipes", description = "Search by title, ingredients, and tags. Ingredients and tags accept comma-separated values.")
    public ResponseEntity<PagedResult<RecipeResponse>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String ingredients,
            @RequestParam(required = false) String tags,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(recipes.search(title, ingredients, tags, page, size));
    }
}
