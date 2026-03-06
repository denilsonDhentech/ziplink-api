package com.dhentech.ziplink_api.application.services.token;
import com.dhentech.ziplink_api.domain.Account;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Account account) {
        Instant now = Instant.now();
        long expiry = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("ziplink-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(account.getId().toString())
                .claim("username", account.getUsername())
                .claim("role", account.getRole())
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
