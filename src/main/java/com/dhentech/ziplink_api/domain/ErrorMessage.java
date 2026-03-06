package com.dhentech.ziplink_api.domain;

public enum ErrorMessage {
    URL_NOT_FOUND("The requested short code does not exist in our database."),
    ALIAS_ALREADY_EXISTS("The provided alias is already being used by another URL."),
    INVALID_URL_FORMAT("The provided original URL is not a valid HTTP/HTTPS address.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
