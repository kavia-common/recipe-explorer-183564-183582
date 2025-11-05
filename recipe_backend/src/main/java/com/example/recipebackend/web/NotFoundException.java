package com.example.recipebackend.web;

/**
 * PUBLIC_INTERFACE
 * NotFound exception for resources.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) { super(msg); }
}
