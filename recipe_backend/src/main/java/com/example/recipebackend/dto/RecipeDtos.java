package com.example.recipebackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

/**
 * DTOs for recipe operations.
 */
public class RecipeDtos {

    // PUBLIC_INTERFACE
    public record RecipeRequest(
            @Schema(description = "Recipe title", example = "Spaghetti Bolognese")
            @NotBlank @Size(min = 3, max = 120) String title,
            @Schema(description = "Recipe description", example = "A classic Italian pasta dish...")
            @NotBlank @Size(min = 10, max = 2000) String description,
            @Schema(description = "Ingredients list", example = "[\"spaghetti\", \"beef\", \"tomato\"]")
            @Size(min = 1) List<@NotBlank String> ingredients,
            @Schema(description = "Tags for filtering", example = "[\"italian\", \"pasta\"]")
            List<@NotBlank String> tags,
            @Schema(description = "Total cooking time in minutes", example = "45")
            @Min(1) Integer cookTimeMinutes
    ) {}

    // PUBLIC_INTERFACE
    public record RecipeResponse(
            Long id,
            String title,
            String description,
            List<String> ingredients,
            List<String> tags,
            Integer cookTimeMinutes,
            Instant createdAt,
            Instant updatedAt,
            Long authorId
    ) {}

    // PUBLIC_INTERFACE
    public record RecipeSearchQuery(
            @Schema(description = "Title contains (case-insensitive)", example = "spaghetti") String title,
            @Schema(description = "Ingredients contain any of", example = "tomato,beef") String ingredients,
            @Schema(description = "Tags contain any of", example = "italian,pasta") String tags,
            @Schema(description = "Page number (0-based)", example = "0") Integer page,
            @Schema(description = "Page size", example = "10") Integer size
    ) {}
}
