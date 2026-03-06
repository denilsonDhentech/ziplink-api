package com.dhentech.ziplink_api.infrastructure.web.exceptions;

import com.dhentech.ziplink_api.domain.ErrorMessage;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException() {
        super(ErrorMessage.URL_NOT_FOUND.getMessage());
    }
}
