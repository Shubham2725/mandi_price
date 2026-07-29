package com.example.repositories;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Powers GET /api/orders — returns only the logged-in user's order history.
     * Ordered by most recent first, which is the natural expectation for
     * an order history screen.
     */
    List<Order> findByUserIdOrderByOrderDateDesc(Long userId);
}