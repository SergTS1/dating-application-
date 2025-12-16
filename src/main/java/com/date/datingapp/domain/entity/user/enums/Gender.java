package com.date.datingapp.domain.entity.user.enums;

import com.date.datingapp.domain.entity.user.UserError;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender from(String value) {
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw UserError.errorInvalidGender();
        }
    }
}
