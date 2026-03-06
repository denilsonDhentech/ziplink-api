package com.dhentech.ziplink_api.domain;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.net.URISyntaxException;

public class Url {

    private final String originalUrl;
    private String shortCode;
    private final LocalDateTime createdAt;

    private static final Pattern URL_PATTERN =
            Pattern.compile("^(https?|ftp)://[^\\s$.?#].[^\\s]*$");

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

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setShortCode(String shortCode) {
        if (this.shortCode != null) {
            throw new IllegalStateException("Short code or alias is already defined");
        }
        this.shortCode = shortCode;
    }
}
