package com.date.datingapp.domain.entity.user;

public record UserName(String value) {
    public UserName {
        if (value == null || value.isBlank())
            throw UserError.errorEmptyUserName();
        if (value.length() < 2)
            throw UserError.errorTooShortName();
        if (value.length() > 20)
            throw UserError.errorTooLongName();
    }

    @Override
    public String toString() {
        return value; }
}
