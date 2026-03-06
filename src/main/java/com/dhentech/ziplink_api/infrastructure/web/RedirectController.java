package com.dhentech.ziplink_api.infrastructure.web;

import com.dhentech.ziplink_api.application.services.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name = "Redirecionamento", description = "Endpoint de acesso aos links curtos")
@RestController
public class RedirectController {

    private final UrlShortenerService service;

    public RedirectController(UrlShortenerService service) {
        this.service = service;
    }

    @Operation(summary = "Redireciona para a URL original", description = "Recebe o código curto e redireciona o usuário para o destino final.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirecionamento bem-sucedido"),
            @ApiResponse(responseCode = "404", description = "Código curto não encontrado")
    })
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = service.getOriginalUrlByCode(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
