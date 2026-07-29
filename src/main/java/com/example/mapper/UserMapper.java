package com.example.mapper;

import com.example.dto.response.RegisterResponse;
import com.example.entity.User;

/**
 * Maps between {@link User} and its outward-facing DTOs.
 * Never maps the password field onto any response DTO.
 */
public final class UserMapper {

    private UserMapper() {
        // utility class
    }

    public static RegisterResponse toRegisterResponse(User user) {
        return new RegisterResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getMobile()
        );
    }
}