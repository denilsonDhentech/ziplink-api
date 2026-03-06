package com.dhentech.ziplink_api.infrastructure.web.login.dto;

public record LoginResponse(
        String token,
        String username,
        String role
) {}
