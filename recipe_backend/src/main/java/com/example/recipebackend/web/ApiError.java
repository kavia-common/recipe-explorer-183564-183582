package com.example.recipebackend.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.Instant;

/**
 * Error payload returned by the global exception handler.
 */
public class ApiError {
    @Schema(description = "HTTP status", example = "400")
    public int status;

    @Schema(description = "Error message")
    public String message;

    @Schema(description = "Timestamp in ISO-8601")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Instant timestamp = Instant.now();

    public ApiError() {}

    public ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
