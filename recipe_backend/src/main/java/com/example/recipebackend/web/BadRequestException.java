package com.example.recipebackend.web;

/**
 * PUBLIC_INTERFACE
 * BadRequest exception for invalid inputs.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) { super(msg); }
}
