package com.date.datingapp.domain.entity.user;

public record PasswordHash(String value) {

    public PasswordHash {
        if (value == null || value.length() == 0 || value.isBlank()) {
            throw UserError.errorIncorrectPasswordHash();
        }
    }
}
