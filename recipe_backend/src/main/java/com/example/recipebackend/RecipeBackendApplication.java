package com.example.recipebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * Entry point for the Recipe Explorer backend.
 * Starts a minimal Spring Boot application exposing actuator health endpoints.
 */
@SpringBootApplication
public class RecipeBackendApplication {

    // PUBLIC_INTERFACE
    /**
     * Application main entrypoint.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RecipeBackendApplication.class, args);
    }
}
