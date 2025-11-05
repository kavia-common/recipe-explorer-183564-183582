package com.example.recipebackend.web;

import com.example.recipebackend.domain.User;
import com.example.recipebackend.dto.AuthDtos;
import com.example.recipebackend.dto.AuthDtos.AuthResponse;
import com.example.recipebackend.dto.AuthDtos.LoginRequest;
import com.example.recipebackend.dto.AuthDtos.RegisterRequest;
import com.example.recipebackend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 PUBLIC_INTERFACE
 REST controller for authentication: register and login.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Registers a new user and returns a mock token")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        User user = auth.register(req);
        // Auto-login after register for demo
        String token = "mock-registered-" + user.getId();
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getUsername()));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticates a user and returns a mock token")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        String token = auth.login(req);
        Long userId = null;
        String username = req.username();
        // In a real app, the token would encode userId; here we find it to include in response.
        // This is not critical for the mock but improves DX.
        // Avoid leaking repository; just return username and null id if needed.
        return ResponseEntity.ok(new AuthResponse(token, userId, username));
    }
}
