package com.example.exceptions;

/**
 * Thrown when a purchase request exceeds available stock.
 * Mapped to HTTP 409 Conflict — the request conflicts with current inventory state.
 */
public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException(String productName, int requested, int available) {
        super("Insufficient stock for '%s': requested %d, only %d available"
                .formatted(productName, requested, available));
    }
}