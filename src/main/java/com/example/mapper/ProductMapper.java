package com.example.mapper;

import com.example.dto.response.ProductResponse;
import com.example.entity.Product;

public final class ProductMapper {

    private ProductMapper() {
        // utility class
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantityAvailable()
        );
    }
}