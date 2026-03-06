package com.dhentech.ziplink_api.infrastructure.config;

import com.dhentech.ziplink_api.application.services.token.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@Configuration
public class SecurityBeanConfig {

    @Bean
    public TokenService tokenService(JwtEncoder jwtEncoder) {
        return new TokenService(jwtEncoder);
    }
}
