package com.example.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        String orderNumber,
        String productName,
        Integer quantity,
        BigDecimal totalPrice,
        LocalDateTime orderDate
) {}