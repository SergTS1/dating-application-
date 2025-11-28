package com.date.datingapp.domain.entity.user;

public record Interests(String value) {

    public Interests {
        if (value == null || value.isBlank())
            throw UserError.errorEmptyInterests();
        if (value.length() > 200)
            throw UserError.errorInterestsTooLageText();
    }

    @Override
    public String toString() {
        return value; }
}
