package com.dhentech.ziplink_api.application.services;

import com.dhentech.ziplink_api.BaseIntegrationTest;
import com.dhentech.ziplink_api.application.dtos.UrlRequest;
import com.dhentech.ziplink_api.domain.repositories.UrlRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlShortenerServiceTest extends BaseIntegrationTest {

    @Autowired
    private UrlShortenerService service;

    @Autowired
    private UrlRepository repository;

    @Test
    @DisplayName("Should handle concurrent requests for the same alias correctly")
    void shouldHandleConcurrency() throws InterruptedException {
        int threadCount = 10;
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        String sharedAlias = "promo-verao";
        String url = "https://alura.com.br";

        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < threadCount; i++) {
                executor.execute(() -> {
                    try {
                        service.shorten(new UrlRequest(url, sharedAlias));
                        successCount.incrementAndGet();
                    } catch (Exception e) {
                        failureCount.incrementAndGet();
                    }
                });
            }
        }

        assertEquals(1, successCount.get(), "Only one request should succeed");
        assertEquals(9, failureCount.get(), "Nine requests should fail due to alias conflict");
    }
}