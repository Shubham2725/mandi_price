package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an application user who can register, log in, and place orders.
 *
 * <p>Design note: {@code orders} is the inverse (non-owning) side of the
 * relationship — {@link Order} owns the foreign key via {@code user_id}.
 * We keep this collection {@code LAZY} and never expose it directly through
 * a DTO to avoid accidental full-collection fetches.</p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 15)
    private String mobile;

    /**
     * BCrypt-hashed password. Never serialized to a DTO or logged.
     */
    @Column(nullable = false)
    @Setter(AccessLevel.PUBLIC)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Keeps both sides of the bidirectional User–Order relationship in sync.
     * Prefer this over calling {@code order.setUser(...)} directly from services.
     */
    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        // Deliberately excludes password and orders (avoid lazy-init exceptions & credential leaks)
        return "User{id=%d, firstName='%s', lastName='%s', email='%s'}"
                .formatted(id, firstName, lastName, email);
    }
}