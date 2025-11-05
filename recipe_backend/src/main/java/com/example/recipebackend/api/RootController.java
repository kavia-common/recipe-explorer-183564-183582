package com.example.recipebackend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PUBLIC_INTERFACE
 * Root controller to provide a simple greeting and basic service info.
 */
@RestController
public class RootController {

    // PUBLIC_INTERFACE
    /**
     * Root endpoint to verify the service is running.
     * @return 200 OK with a simple JSON response
     */
    @GetMapping("/")
    public ResponseEntity<?> root() {
        return ResponseEntity.ok(java.util.Map.of(
                "message", "Recipe Explorer backend is running",
                "service", "recipe-backend",
                "status", "ok"
        ));
    }
}
