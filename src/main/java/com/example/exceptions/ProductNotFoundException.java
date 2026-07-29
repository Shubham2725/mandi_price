package com.example.exceptions;

/**
 * Thrown when a referenced product ID does not exist.
 * Mapped to HTTP 404 Not Found.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Product not found with id: " + productId);
    }
}