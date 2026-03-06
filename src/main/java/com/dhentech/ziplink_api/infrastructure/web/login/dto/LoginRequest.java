package com.dhentech.ziplink_api.infrastructure.web.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Schema(description = "Nome de usuário do sistema", example = "admin")
        @NotBlank(message = "Username is required")
        String username,

        @Schema(description = "Senha de acesso", example = "admin123")
        @NotBlank(message = "Password is required")
        String password
) {
}
