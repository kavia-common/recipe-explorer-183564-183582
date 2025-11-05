package com.example.recipebackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTOs for auth endpoints.
 */
public class AuthDtos {

    // PUBLIC_INTERFACE
    public record RegisterRequest(
            @Schema(description = "Unique username", example = "chefjane")
            @NotBlank String username,
            @Schema(description = "Email address", example = "jane@example.com")
            @NotBlank @Email String email,
            @Schema(description = "Password (plain text for demo; would be hashed server-side)", example = "secret123")
            @NotBlank String password
    ) {}

    // PUBLIC_INTERFACE
    public record LoginRequest(
            @Schema(description = "Username", example = "chefjane")
            @NotBlank String username,
            @Schema(description = "Password", example = "secret123")
            @NotBlank String password
    ) {}

    // PUBLIC_INTERFACE
    public record AuthResponse(
            @Schema(description = "Mock auth token", example = "mock-token-abc123")
            String token,
            @Schema(description = "Authenticated user id", example = "1")
            Long userId,
            @Schema(description = "Authenticated username", example = "chefjane")
            String username
    ) {}
}
