package com.example.service;

import com.example.dto.response.ProductResponse;

import java.util.List;

/**
 * Handles product catalog retrieval.
 */
public interface ProductService {

    /**
     * Returns all available products.
     */
    List<ProductResponse> getAllProducts();
}
