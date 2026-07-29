package com.example.repositories;

import com.example.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Used by CustomUserDetailsService to load a user during authentication,
     * and by AuthenticationServiceImpl to fetch the logged-in user for order operations.
     */
    Optional<User> findByEmail(String email);

    /**
     * Used during registration to enforce email uniqueness without
     * hydrating a full User entity.
     */
    boolean existsByEmail(String email);
}