package com.date.datingapp.domain.entity.user;

public record Email(String value) {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public Email {
        if (value == null || !value.matches(EMAIL_PATTERN)) {
            throw UserError.errIncorrectEmail();
        }
    }
}
