package com.dhentech.ziplink_api.application.services.login;

import com.dhentech.ziplink_api.application.services.token.TokenService;
import com.dhentech.ziplink_api.domain.Account;
import com.dhentech.ziplink_api.domain.repositories.AccountRepository;
import com.dhentech.ziplink_api.infrastructure.web.login.dto.LoginRequest;
import com.dhentech.ziplink_api.infrastructure.web.login.dto.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public AuthService(AccountRepository accountRepository,
                       PasswordEncoder passwordEncoder,
                       TokenService tokenService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponse authenticate(LoginRequest request) {
        log.info("Iniciando tentativa de autenticação para o usuário: {}", request.username());

        Account account = accountRepository.findByUsername(request.username())
                .orElseThrow(() -> {
                    log.warn("Login falhou: usuário {} não encontrado", request.username());
                    return new RuntimeException("Invalid username or password");
                });

        if (!passwordEncoder.matches(request.password(), account.getPassword())) {
            log.warn("Senha incorreta fornecida para o usuário: {}", request.username());
            throw new RuntimeException("Invalid username or password");
        }

        try {
            log.debug("Gerando token para {}", account.getUsername());
            String token = tokenService.generateToken(account);

            log.info("Usuário {} autenticado com sucesso!", account.getUsername());

            return new LoginResponse(
                    token,
                    account.getUsername(),
                    account.getRole()
            );
        } catch (Exception e) {
            log.error("Erro ao processar autenticação de {}: {}", account.getUsername(), e.getMessage());
            throw new RuntimeException("Internal error during authentication");
        }
    }
}
