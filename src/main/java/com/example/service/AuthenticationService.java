package com.example.service;

import com.example.dto.request.LoginRequest;
import com.example.dto.request.RegisterRequest;
import com.example.dto.response.LoginResponse;
import com.example.dto.response.RegisterResponse;

/**
 * Handles user registration and login, including password matching,
 * uniqueness checks, and JWT issuance.
 */
public interface AuthenticationService {

    /**
     * Registers a new user after validating email uniqueness and
     * password/confirmPassword match.
     *
     * @throws com.fruitstore.exception.DuplicateEmailException if the email is already registered
     * @throws IllegalArgumentException if password and confirmPassword do not match
     */
    RegisterResponse register(RegisterRequest request);

    /**
     * Authenticates a user and issues a JWT access token.
     *
     * @throws com.fruitstore.exception.InvalidCredentialsException if credentials are invalid
     */
    LoginResponse login(LoginRequest request);
}
