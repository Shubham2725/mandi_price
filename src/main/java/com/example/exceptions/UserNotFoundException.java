package com.example.exceptions;

/**
 * Thrown when a user referenced by ID or email cannot be found.
 * Mapped to HTTP 404 Not Found.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String identifier) {
        super("User not found: " + identifier);
    }
}