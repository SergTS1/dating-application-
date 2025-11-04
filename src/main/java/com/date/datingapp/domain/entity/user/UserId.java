package com.date.datingapp.domain.entity.user;

import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
        if (value == null) {
            throw UserError.errorUserIdCannotBeNull();
        }
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

}
