package com.dhentech.ziplink_api.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.net.URISyntaxException;

@Entity
@Table(name = "tb_url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Original URL is mandatory")
    @Size(max = 2048)
    @Column(name = "original_url", nullable = false, length = 2048)
    private String originalUrl;

    @NotBlank(message = "Short code is mandatory")
    @Size(min = 3, max = 20)
    @Column(name = "short_code", nullable = false, unique = true, length = 20)
    private String shortCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private static final Pattern URL_PATTERN =
            Pattern.compile("^(https?|ftp)://[^\\s$.?#].[^\\s]*$");

    protected Url() {}

    public Url(String originalUrl, String alias) {
        validateUrl(originalUrl);
        this.originalUrl = originalUrl;
        this.shortCode = alias;
        this.createdAt = LocalDateTime.now();
    }

    private void validateUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Invalid original URL format");
        }

        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();

            if (scheme == null || (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https"))) {
                throw new IllegalArgumentException("Invalid original URL format");
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid original URL format");
        }
    }

    public Long getId() { return id; }
    public String getOriginalUrl() { return originalUrl; }
    public String getShortCode() { return shortCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setShortCode(String shortCode) {
        if (this.shortCode != null) {
            throw new IllegalStateException("Short code or alias is already defined");
        }
        this.shortCode = shortCode;
    }
}
