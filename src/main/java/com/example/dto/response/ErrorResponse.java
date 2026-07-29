package com.example.dto.response;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Standard error shape returned by GlobalExceptionHandler for every
 * failure case (validation errors, business exceptions, auth failures).
 *
 * @param status    HTTP status code (e.g. 400, 404, 401)
 * @param error     Short error label (e.g. "Bad Request", "Not Found")
 * @param message   Human-readable summary message
 * @param details   Field-level validation messages, if applicable (empty otherwise)
 * @param timestamp When the error occurred
 * @param path      The request path that caused the error
 */
public record ErrorResponse(
        int status,
        String error,
        String message,
        List<String> details,
        LocalDateTime timestamp,
        String path
) {
    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(status, error, message, List.of(), LocalDateTime.now(), path);
    }

    public static ErrorResponse of(int status, String error, String message, List<String> details, String path) {
        return new ErrorResponse(status, error, message, details, LocalDateTime.now(), path);
    }
}