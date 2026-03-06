package com.dhentech.ziplink_api.infrastructure.web;

import com.dhentech.ziplink_api.application.dtos.UrlRequest;
import com.dhentech.ziplink_api.application.dtos.UrlResponse;
import com.dhentech.ziplink_api.application.services.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Encurtador", description = "Endpoints para criação de links curtos")
@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {

    private final UrlShortenerService urlShortenerService;

    public UrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @Operation(summary = "Encurta uma nova URL", description = "Recebe uma URL longa e um alias opcional e retorna o link encurtado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "URL encurtada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Alias já está em uso")
    })
    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shorten(@RequestBody @Valid UrlRequest request) {
        UrlResponse response = urlShortenerService.shorten(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}