package com.dhentech.ziplink_api.infrastructure.config;

import com.dhentech.ziplink_api.application.services.UrlShortenerService;
import com.dhentech.ziplink_api.domain.repositories.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class BeanConfiguration {

    @Bean
    public ReentrantLock reentrantLock() {
        return new ReentrantLock();
    }

    @Bean
    @Transactional
    public UrlShortenerService urlShortenerService(UrlRepository urlRepository, ReentrantLock lock) {
        return new UrlShortenerService(urlRepository, lock);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}