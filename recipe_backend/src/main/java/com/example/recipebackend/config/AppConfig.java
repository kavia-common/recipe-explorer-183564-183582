package com.example.recipebackend.config;

import com.example.recipebackend.domain.Recipe;
import com.example.recipebackend.domain.User;
import com.example.recipebackend.repository.RecipeRepository;
import com.example.recipebackend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        UserRepository repo = new UserRepository();
        // Seed users
        repo.save(new User(null, "chefjane", "jane@example.com", "{noop}secret123", Set.of("USER")));
        repo.save(new User(null, "chefjohn", "john@example.com", "{noop}password", Set.of("USER")));
        return repo;
    }

    @Bean
    public RecipeRepository recipeRepository(UserRepository users) {
        RecipeRepository repo = new RecipeRepository();
        // Seed recipes
        Long janeId = users.findByUsername("chefjane").map(User::getId).orElse(1L);
        Long johnId = users.findByUsername("chefjohn").map(User::getId).orElse(2L);

        repo.save(new Recipe(null, "Spaghetti Bolognese", "Classic pasta with rich meat sauce.",
                List.of("spaghetti", "beef", "tomato", "onion", "garlic"),
                List.of("italian", "pasta"),
                45, Instant.now().minusSeconds(86400), Instant.now().minusSeconds(3600), janeId));

        repo.save(new Recipe(null, "Avocado Toast", "Quick and healthy breakfast option.",
                List.of("bread", "avocado", "lemon", "salt", "pepper"),
                List.of("breakfast", "healthy", "quick"),
                10, Instant.now().minusSeconds(7200), Instant.now().minusSeconds(1800), johnId));

        repo.save(new Recipe(null, "Chicken Curry", "A flavorful curry with tender chicken pieces.",
                List.of("chicken", "curry powder", "coconut milk", "onion", "garlic"),
                List.of("indian", "spicy"),
                60, Instant.now().minusSeconds(200000), Instant.now().minusSeconds(5000), janeId));

        return repo;
    }
}
