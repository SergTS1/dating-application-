package com.date.datingapp.usecase.user;

import com.date.datingapp.domain.entity.user.photo.PhotoId;
import com.date.datingapp.infra.logger.Logger;
import com.date.datingapp.shared.exception.CodedException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class UserUseCaseError {

    public static final String USER_NOT_FOUND = "0199ca11-001";
    public static final String PHOTO_NOT_FOUND = "0199ca11-002";
    public static final String REQUIRED_PARAMS = "0199ca11-003";
    public static final String USER_ALREADY_EXISTS = "0199ca11-004";

    private final Logger logger;

    public UserUseCaseError(Logger logger) {
        this.logger = logger;
    }

    public CodedException userNotFound(UUID uuid) {
        var message = String.format("User with uuid %s not found", uuid.toString());
        var ex = new CodedException(USER_NOT_FOUND, message);
        logger.error(message, ex);
        return ex;
    }

    public CodedException photoNotFound(PhotoId photoId) {
        var message = String.format("Photo with ID %s not found", photoId.toString());
        var ex = new CodedException(PHOTO_NOT_FOUND, message);
        logger.error(message, ex);
        return ex;
    }

    public CodedException paramsAreRequired() {
        var message = "Params are required";
        return new CodedException(REQUIRED_PARAMS, message);
    }

    public CodedException userAlreadyExists() {
        var message = "User already exists";
        return new CodedException(USER_ALREADY_EXISTS, message);
    }
}
