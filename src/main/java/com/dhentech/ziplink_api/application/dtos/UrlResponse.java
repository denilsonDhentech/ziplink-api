package com.dhentech.ziplink_api.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UrlResponse(
        @Schema(description = "URL original") String originalUrl,
        @Schema(description = "Código curto gerado ou alias", example = "google-busca") String shortCode,
        @Schema(description = "URL encurtada completa", example = "http://localhost:8080/google-busca") String shortenedUrl,
        @Schema(description = "Data e hora da criação") LocalDateTime createdAt
) {}