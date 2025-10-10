package com.warrantyclaim.warrantyclaim_api.dto;

import java.util.List;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        List<ValidationError> errors
) {

    // Nested record cho loi validation
    public record ValidationError(String field, String message) {
    }

    // factory methods
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> error(String message, List<ValidationError> errors) {
        return new ApiResponse<>(false, message, null, errors);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, null);
    }

    public static <T> ApiResponse<T> validationError(String message, List<ValidationError> errors) {
        return new ApiResponse<>(false, message, null, errors);
    }
}