package com.example.dto.response;

public record RegisterResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String mobile
) {}