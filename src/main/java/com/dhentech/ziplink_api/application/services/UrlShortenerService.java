package com.dhentech.ziplink_api.application.services;

import com.dhentech.ziplink_api.application.dtos.UrlRequest;
import com.dhentech.ziplink_api.application.dtos.UrlResponse;
import com.dhentech.ziplink_api.domain.Url;
import com.dhentech.ziplink_api.domain.repositories.UrlRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class UrlShortenerService {

    private final UrlRepository repository;
    private final ReentrantLock lock;

    public UrlShortenerService(UrlRepository repository, ReentrantLock lock) {
        this.repository = repository;
        this.lock = lock;
    }

    @Transactional
    public UrlResponse shorten(UrlRequest request) {
        lock.lock();
        try {
            String code = (request.alias() != null && !request.alias().isBlank())
                    ? request.alias()
                    : UUID.randomUUID().toString().substring(0, 6);

            if (repository.existsByShortCode(code)) {
                throw new IllegalArgumentException("Short code or alias already in use: " + code);
            }

            Url url = new Url(request.originalUrl(), code);
            Url savedUrl = repository.save(url);

            return new UrlResponse(
                    savedUrl.getOriginalUrl(),
                    savedUrl.getShortCode(),
                    "http://localhost:8080/" + savedUrl.getShortCode(),
                    savedUrl.getCreatedAt()
            );
        } finally {
            lock.unlock();
        }
    }

    public String getOriginalUrlByCode(String code) {
        return repository.findByShortCode(code)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found for code: " + code));
    }
}