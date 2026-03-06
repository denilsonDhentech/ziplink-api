package com.dhentech.ziplink_api.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UrlRequest(
        @NotBlank(message = "URL is required")
        String originalUrl,

        @Size(max = 20, message = "Alias must be at most 20 characters")
        String alias
){ }
