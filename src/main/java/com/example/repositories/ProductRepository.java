package com.example.repositories;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // No custom queries needed yet — findAll() and findById() from JpaRepository
    // cover GET /api/products and the product lookup during order placement.
}