package com.example.service;


import com.example.dto.request.OrderRequest;
import com.example.dto.response.OrderResponse;

import java.util.List;

/**
 * Handles order placement and order history retrieval for the
 * currently authenticated user.
 */
public interface OrderService {

    /**
     * Places an order for the given product/quantity on behalf of the
     * authenticated user identified by {@code userEmail}.
     *
     * @throws com.fruitstore.exception.ProductNotFoundException if the product doesn't exist
     * @throws com.fruitstore.exception.InsufficientQuantityException if stock is insufficient
     */
    OrderResponse placeOrder(String userEmail, OrderRequest request);

    /**
     * Returns the full order history for the authenticated user,
     * most recent first.
     */
    List<OrderResponse> getOrderHistory(String userEmail);
}
