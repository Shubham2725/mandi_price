package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a purchasable fruit product with available inventory.
 *
 * <p>Design note: {@code quantityAvailable} mutation (decrement on purchase)
 * is intentionally handled in {@code OrderServiceImpl}, not here, to keep
 * the entity a plain data holder and the business rule (insufficient stock
 * check) visible in the service layer where it belongs.</p>
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantityAvailable;

    public Product(String name, String description, BigDecimal price, Integer quantityAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    /**
     * Decrements available stock. Caller is responsible for verifying
     * sufficient quantity beforehand (see InsufficientQuantityException).
     */
    public void reduceStock(int quantity) {
        this.quantityAvailable -= quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id != null && id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{id=%d, name='%s', price=%s, quantityAvailable=%d}"
                .formatted(id, name, price, quantityAvailable);
    }
}