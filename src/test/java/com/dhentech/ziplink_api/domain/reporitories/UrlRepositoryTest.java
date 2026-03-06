package com.dhentech.ziplink_api.domain.reporitories;
import com.dhentech.ziplink_api.domain.Url;
import com.dhentech.ziplink_api.domain.repositories.UrlRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UrlRepositoryTest {

    @Autowired
    private UrlRepository repository;

    @Test
    @DisplayName("Should persist and find a URL by short code")
    void shouldPersistAndFindByShortCode() {
        String originalUrl = "https://google.com";
        String shortCode = "gogl123";
        Url url = new Url(originalUrl, shortCode);

        repository.save(url);

        Optional<Url> found = repository.findByShortCode(shortCode);

        assertTrue(found.isPresent());
        assertEquals(originalUrl, found.get().getOriginalUrl());
    }

    @Test
    @DisplayName("Should return true when short code already exists")
    void shouldReturnTrueWhenShortCodeExists() {
        String shortCode = "exists";
        Url url = new Url("https://test.com", shortCode);
        repository.save(url);

        boolean exists = repository.existsByShortCode(shortCode);

        assertTrue(exists);
    }
}
