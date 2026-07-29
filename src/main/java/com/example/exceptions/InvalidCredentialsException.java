package com.example.exceptions;

/**
 * Thrown on login when email/password do not match.
 * Mapped to HTTP 401 Unauthorized.
 *
 * <p>Deliberately does NOT indicate whether the email exists or the
 * password was wrong — revealing that distinguishes valid emails from
 * invalid ones and helps an attacker enumerate registered accounts.</p>
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid email or password");
    }
}