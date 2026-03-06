package com.dhentech.ziplink_api.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UrlRequest(
        @Schema(description = "URL original que será encurtada", example = "https://www.google.com")
        @NotBlank @URL String originalUrl,

        @Schema(description = "Alias personalizado (opcional)", example = "google-busca", maxLength = 20)
        @Size(max = 20) String alias
) {}
