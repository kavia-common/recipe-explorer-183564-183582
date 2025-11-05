package com.example.recipebackend.service;

import com.example.recipebackend.domain.User;
import com.example.recipebackend.dto.AuthDtos.LoginRequest;
import com.example.recipebackend.dto.AuthDtos.RegisterRequest;
import com.example.recipebackend.repository.UserRepository;
import com.example.recipebackend.web.BadRequestException;
import com.example.recipebackend.web.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository users;

    public AuthService(UserRepository users) {
        this.users = users;
    }

    // PUBLIC_INTERFACE
    public User register(RegisterRequest req) {
        if (users.existsByUsername(req.username())) {
            throw new BadRequestException("Username already exists");
        }
        // Basic password handling for demo; include {noop} so Spring style is evident for future upgrades
        User u = new User(null, req.username(), req.email(), "{noop}" + req.password(), Set.of("USER"));
        return users.save(u);
    }

    // PUBLIC_INTERFACE
    public String login(LoginRequest req) {
        User user = users.findByUsername(req.username())
                .orElseThrow(() -> new NotFoundException("Invalid username or password"));
        // For demo, check plain suffix after {noop}
        String stored = user.getPasswordHash();
        String provided = "{noop}" + req.password();
        if (!provided.equals(stored)) {
            throw new NotFoundException("Invalid username or password");
        }
        // Return mock token (random UUID)
        return "mock-" + UUID.randomUUID();
    }
}
