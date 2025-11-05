package com.example.recipebackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI recipeExplorerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recipe Explorer API")
                        .version("0.1.0")
                        .description("REST API for authentication, recipe browsing, CRUD, and search.")
                        .contact(new Contact().name("Recipe Explorer").email("support@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"));
    }
}
