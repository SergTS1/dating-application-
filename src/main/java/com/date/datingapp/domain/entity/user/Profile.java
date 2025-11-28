package com.date.datingapp.domain.entity.user;

import com.date.datingapp.domain.entity.user.enums.Gender;

import java.util.Objects;

public record Profile(UserName name, Gender gender, Interests interests) {

    private static final String GENDER_CANNOT_BE_EMPTY = "Gender must be provided";

    public Profile {
        Objects.requireNonNull(name, UserError.NAME_CANNOT_BE_EMPTY);
        Objects.requireNonNull(gender, GENDER_CANNOT_BE_EMPTY);
        Objects.requireNonNull(interests, UserError.INTERESTS_CANNOT_BE_EMPTY);
    }
}
