package com.dhentech.ziplink_api.application.dtos;

import java.time.LocalDateTime;

public record UrlResponse(
        String originalUrl,
        String shortCode,
        String shortenedUrl,
        LocalDateTime createdAt
) {}