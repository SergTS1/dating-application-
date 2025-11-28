package com.date.datingapp.domain.entity.user.photo;

import com.date.datingapp.domain.entity.user.UserError;

import java.util.UUID;

public record PhotoId(UUID value) {

    public PhotoId {
        if (value == null) {
            throw UserError.errorPhotoIdCannotBeNull();
        }
    }

    public static PhotoId generate() {
        return new PhotoId(UUID.randomUUID());
    }
}
