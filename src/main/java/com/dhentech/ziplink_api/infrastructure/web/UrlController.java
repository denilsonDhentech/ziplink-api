package com.dhentech.ziplink_api.infrastructure.web;

import com.dhentech.ziplink_api.application.dtos.UrlRequest;
import com.dhentech.ziplink_api.application.dtos.UrlResponse;
import com.dhentech.ziplink_api.application.services.UrlShortenerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {

    private final UrlShortenerService urlShortenerService;

    public UrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shorten(@RequestBody @Valid UrlRequest request) {
        UrlResponse response = urlShortenerService.shorten(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}