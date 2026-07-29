package com.example.dto.request;

import jakarta.validation.constraints.*;

/**
 * Payload for new user registration.
 *
 * <p>Password/confirmPassword match validation is intentionally NOT done
 * via a class-level annotation here — it's handled explicitly in the
 * service layer, where a mismatch can be reported with a clear, specific
 * error message rather than a generic cross-field validator.</p>
 */
public record RegisterRequest(

        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name must not exceed 50 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name must not exceed 50 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be a valid email address")
        String email,

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
        String mobile,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit"
        )
        String password,

        @NotBlank(message = "Confirm password is required")
        String confirmPassword

) {}