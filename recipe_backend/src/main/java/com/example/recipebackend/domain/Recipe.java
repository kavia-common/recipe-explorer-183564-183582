package com.example.recipebackend.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private Long id;
    private String title;
    private String description;
    private List<String> ingredients = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private Integer cookTimeMinutes;
    private Instant createdAt;
    private Instant updatedAt;
    private Long authorId;

    public Recipe() {}

    public Recipe(Long id, String title, String description, List<String> ingredients, List<String> tags,
                  Integer cookTimeMinutes, Instant createdAt, Instant updatedAt, Long authorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        if (ingredients != null) this.ingredients = ingredients;
        if (tags != null) this.tags = tags;
        this.cookTimeMinutes = cookTimeMinutes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorId = authorId;
    }

    public Long getId() { return id; }

    public Recipe setId(Long id) { this.id = id; return this; }

    public String getTitle() { return title; }

    public Recipe setTitle(String title) { this.title = title; return this; }

    public String getDescription() { return description; }

    public Recipe setDescription(String description) { this.description = description; return this; }

    public List<String> getIngredients() { return ingredients; }

    public Recipe setIngredients(List<String> ingredients) { this.ingredients = ingredients; return this; }

    public List<String> getTags() { return tags; }

    public Recipe setTags(List<String> tags) { this.tags = tags; return this; }

    public Integer getCookTimeMinutes() { return cookTimeMinutes; }

    public Recipe setCookTimeMinutes(Integer cookTimeMinutes) { this.cookTimeMinutes = cookTimeMinutes; return this; }

    public Instant getCreatedAt() { return createdAt; }

    public Recipe setCreatedAt(Instant createdAt) { this.createdAt = createdAt; return this; }

    public Instant getUpdatedAt() { return updatedAt; }

    public Recipe setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; return this; }

    public Long getAuthorId() { return authorId; }

    public Recipe setAuthorId(Long authorId) { this.authorId = authorId; return this; }
}
