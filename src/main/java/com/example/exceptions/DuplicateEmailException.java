package com.example.exceptions;

/**
 * Thrown during registration when the email is already in use.
 * Mapped to HTTP 409 Conflict — the request is well-formed but conflicts
 * with existing server state.
 */
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("An account with email '%s' already exists".formatted(email));
    }
}