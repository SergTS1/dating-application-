package com.date.datingapp.domain.entity.user.photo;

import com.date.datingapp.domain.entity.user.UserError;

import java.net.MalformedURLException;
import java.net.URL;

public record PhotoUrl(String value) {

    public PhotoUrl {
        if (value == null) {
            throw UserError.errorPhotoUrlCannotBeNull();
        }

        if (value.isBlank()) {
            throw UserError.errorPhotoUrlCannotBeBlank();
        }

        URL parsed;
        try {
            parsed = new URL(value);
        } catch (MalformedURLException e) {
            throw UserError.errorInvalidUrl(value);
        }

        if (!parsed.getProtocol().equals("http") &&
                !parsed.getProtocol().equals("https")) {
            throw UserError.errorNotAllowedUrl();
        }
    }
}
