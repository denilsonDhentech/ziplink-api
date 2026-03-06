package com.dhentech.ziplink_api.domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UrlTest {

    @Test
    @DisplayName("Should create a URL instance with an alias as short code")
    void shouldCreateUrlWithAlias() {
        String originalUrl = "https://www.alura.com.br";
        String alias = "alura-course";

        Url url = new Url(originalUrl, alias);

        assertEquals(originalUrl, url.getOriginalUrl());
        assertEquals(alias, url.getShortCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "invalid-url", "ftp://wrong.com"})
    @DisplayName("Should throw exception for invalid URL: {0}")
    void shouldThrowExceptionForInvalidUrl(String invalidUrl) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Url(invalidUrl, "my-alias");
        }, "Failed for value: " + invalidUrl);
    }

    @Test
    @DisplayName("Should allow null alias (it will be generated later)")
    void shouldAllowNullAlias() {
        Url url = new Url("https://google.com", null);
        assertNull(url.getShortCode());
    }
}
