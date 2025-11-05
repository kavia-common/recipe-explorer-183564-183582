package com.example.recipebackend.service;

import com.example.recipebackend.domain.Recipe;
import com.example.recipebackend.dto.RecipeDtos.RecipeRequest;
import com.example.recipebackend.dto.RecipeDtos.RecipeResponse;
import com.example.recipebackend.repository.RecipeRepository;
import com.example.recipebackend.web.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipes;

    public RecipeService(RecipeRepository recipes) {
        this.recipes = recipes;
    }

    private RecipeResponse toDto(Recipe r) {
        return new RecipeResponse(
                r.getId(), r.getTitle(), r.getDescription(), r.getIngredients(), r.getTags(),
                r.getCookTimeMinutes(), r.getCreatedAt(), r.getUpdatedAt(), r.getAuthorId()
        );
    }

    // PUBLIC_INTERFACE
    public RecipeResponse create(RecipeRequest req, Long authorId) {
        Recipe r = new Recipe();
        r.setTitle(req.title())
         .setDescription(req.description())
         .setIngredients(req.ingredients())
         .setTags(req.tags())
         .setCookTimeMinutes(req.cookTimeMinutes())
         .setAuthorId(authorId)
         .setCreatedAt(Instant.now())
         .setUpdatedAt(Instant.now());
        recipes.save(r);
        return toDto(r);
    }

    // PUBLIC_INTERFACE
    public RecipeResponse get(Long id) {
        Recipe r = recipes.findById(id).orElseThrow(() -> new NotFoundException("Recipe not found"));
        return toDto(r);
    }

    // PUBLIC_INTERFACE
    public RecipeResponse update(Long id, RecipeRequest req) {
        Recipe r = recipes.findById(id).orElseThrow(() -> new NotFoundException("Recipe not found"));
        r.setTitle(req.title());
        r.setDescription(req.description());
        r.setIngredients(req.ingredients());
        r.setTags(req.tags());
        r.setCookTimeMinutes(req.cookTimeMinutes());
        r.setUpdatedAt(Instant.now());
        recipes.save(r);
        return toDto(r);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        recipes.findById(id).orElseThrow(() -> new NotFoundException("Recipe not found"));
        recipes.deleteById(id);
    }

    // PUBLIC_INTERFACE
    public PagedResult<RecipeResponse> list(int page, int size) {
        List<Recipe> all = recipes.findAll().stream()
                .sorted((a, b) -> {
                    Instant ai = a.getCreatedAt();
                    Instant bi = b.getCreatedAt();
                    if (ai == null && bi == null) return 0;
                    if (ai == null) return 1;
                    if (bi == null) return -1;
                    return bi.compareTo(ai);
                }).toList();
        int from = Math.max(page * size, 0);
        int to = Math.min(from + size, all.size());
        List<RecipeResponse> data = from >= all.size() ? List.of()
                : all.subList(from, to).stream().map(this::toDto).toList();
        return new PagedResult<>(data, all.size(), page, size);
    }

    // PUBLIC_INTERFACE
    public PagedResult<RecipeResponse> search(String title, String ingredientsCsv, String tagsCsv, int page, int size) {
        Set<String> ingredients = csvToSet(ingredientsCsv);
        Set<String> tags = csvToSet(tagsCsv);
        List<Recipe> list = recipes.search(title, ingredients, tags, page, size);
        long total = recipes.countSearch(title, ingredients, tags);
        List<RecipeResponse> data = list.stream().map(this::toDto).toList();
        return new PagedResult<>(data, total, page, size);
    }

    private Set<String> csvToSet(String csv) {
        if (csv == null || csv.isBlank()) return Set.of();
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toSet());
    }

    // PUBLIC_INTERFACE
    public record PagedResult<T>(List<T> items, long total, int page, int size) {}
}
