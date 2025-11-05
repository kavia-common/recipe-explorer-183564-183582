package com.example.recipebackend.repository;

import com.example.recipebackend.domain.Recipe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class RecipeRepository {
    private final Map<Long, Recipe> recipes = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public Recipe save(Recipe recipe) {
        if (recipe.getId() == null) {
            recipe.setId(seq.incrementAndGet());
        }
        recipes.put(recipe.getId(), recipe);
        return recipe;
    }

    public Optional<Recipe> findById(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    public void deleteById(Long id) {
        recipes.remove(id);
    }

    public List<Recipe> findAll() {
        return new ArrayList<>(recipes.values());
    }

    public List<Recipe> search(String titleContains, Set<String> ingredientsAny, Set<String> tagsAny,
                               int page, int size) {
        Stream<Recipe> stream = recipes.values().stream();

        if (titleContains != null && !titleContains.isBlank()) {
            String needle = titleContains.toLowerCase();
            stream = stream.filter(r -> r.getTitle() != null && r.getTitle().toLowerCase().contains(needle));
        }
        if (ingredientsAny != null && !ingredientsAny.isEmpty()) {
            Set<String> lower = new HashSet<>();
            ingredientsAny.forEach(s -> lower.add(s.toLowerCase()));
            stream = stream.filter(r -> r.getIngredients() != null &&
                    r.getIngredients().stream().map(String::toLowerCase).anyMatch(lower::contains));
        }
        if (tagsAny != null && !tagsAny.isEmpty()) {
            Set<String> lower = new HashSet<>();
            tagsAny.forEach(s -> lower.add(s.toLowerCase()));
            stream = stream.filter(r -> r.getTags() != null &&
                    r.getTags().stream().map(String::toLowerCase).anyMatch(lower::contains));
        }

        List<Recipe> filtered = stream.sorted(Comparator.comparing(Recipe::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder())).reversed()).toList();
        int from = Math.max(page * size, 0);
        int to = Math.min(from + size, filtered.size());
        if (from >= filtered.size()) return Collections.emptyList();
        return filtered.subList(from, to);
    }

    public long countSearch(String titleContains, Set<String> ingredientsAny, Set<String> tagsAny) {
        return search(titleContains, ingredientsAny, tagsAny, 0, Integer.MAX_VALUE).size();
    }
}
