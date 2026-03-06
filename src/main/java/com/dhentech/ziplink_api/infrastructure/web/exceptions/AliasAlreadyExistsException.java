package com.dhentech.ziplink_api.infrastructure.web.exceptions;

import com.dhentech.ziplink_api.domain.ErrorMessage;

public class AliasAlreadyExistsException extends RuntimeException {
    public AliasAlreadyExistsException() {
        super(ErrorMessage.ALIAS_ALREADY_EXISTS.getMessage());
    }
}
