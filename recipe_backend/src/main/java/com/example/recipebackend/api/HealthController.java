package com.example.recipebackend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PUBLIC_INTERFACE
 * Basic API to confirm the service is up.
 */
@RestController
public class HealthController {

    // PUBLIC_INTERFACE
    /**
     * Returns a basic status payload to verify the service is running.
     * @return 200 OK with a simple JSON map
     */
    @GetMapping("/api/status")
    public ResponseEntity<?> status() {
        return ResponseEntity.ok(java.util.Map.of("status", "ok", "service", "recipe-backend"));
    }
}
