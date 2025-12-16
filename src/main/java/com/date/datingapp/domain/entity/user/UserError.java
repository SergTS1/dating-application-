package com.date.datingapp.domain.entity.user;

import com.date.datingapp.shared.exception.CodedException;

public class UserError {

    public static final String PREMIUM_CANNOT_BE_ACTIVATED = "0199d2a5-001";
    public static final String INCORRECT_EMAIL = "0199d2a5-002";
    public static final String INCORRECT_PASSWORD_HASH = "0199d2a5-003";
    public static final String PHOTO_ID_CANNOT_BE_NULL = "0199d2a5-004";
    public static final String USER_ID_CANNOT_BE_NULL = "0199d2a5-005";
    public static final String PHOTO_URL_CANNOT_BE_NULL = "0199d2a5-006";
    public static final String PHOTO_URL_CANNOT_BE_BLANK = "0199d2a5-007";
    public static final String INVALID_PHOTO_URL_FORMAT = "0199d2a5-008";
    public static final String NOT_ALLOWED_URL = "0199d2a5-009";
    public static final String NAME_CANNOT_BE_EMPTY = "0199d2a5-010";
    public static final String TOO_SHORT_NAME = "0199d2a5-011";
    public static final String TOO_LONG_NAME = "0199d2a5-012";
    public static final String INTERESTS_CANNOT_BE_EMPTY = "0199d2a5-013";
    public static final String INTERESTS_TOO_LAGE_TEXT = "0199d2a5-014";
    public static final String GENDER_INVALID = "0199d2a5-015";

    private UserError() {
    }

    public static CodedException errCannotBeActivated() {
        var msg = "Premium cannot be activated without user verification";
        return new CodedException(PREMIUM_CANNOT_BE_ACTIVATED, msg);
    }

    public static CodedException errIncorrectEmail() {
        var msg = "Некорректный email";
        return new CodedException(INCORRECT_EMAIL, msg);
    }

    public static CodedException errorIncorrectPasswordHash() {
        var msg = "некорректный Password Hash";
        return new CodedException(INCORRECT_PASSWORD_HASH, msg);
    }

    public static CodedException errorPhotoIdCannotBeNull() {
        var msg = "PhotoId cannot be null";
        return new CodedException(PHOTO_ID_CANNOT_BE_NULL, msg);
    }

    public static CodedException errorUserIdCannotBeNull() {
        var msg = "UserId cannot be null";
        return new CodedException(USER_ID_CANNOT_BE_NULL, msg);
    }

    public static CodedException errorPhotoUrlCannotBeNull() {
        var msg = "Photo URL cannot be null";
        return new CodedException(PHOTO_URL_CANNOT_BE_NULL, msg);
    }

    public static CodedException errorPhotoUrlCannotBeBlank() {
        var msg = "Photo URL cannot be blank";
        return new CodedException(PHOTO_URL_CANNOT_BE_BLANK, msg);
    }

    public static CodedException errorInvalidUrl(String value) {
        var msg = "Invalid photo URL format: ";
        return new CodedException(INVALID_PHOTO_URL_FORMAT, msg + value);
    }

    public static CodedException errorNotAllowedUrl() {
        var msg = "Only http/https URLs are allowed";
        return new CodedException(NOT_ALLOWED_URL, msg);
    }

    public static CodedException errorEmptyUserName() {
        var msg = "Name cannot be empty";
        return new CodedException(NAME_CANNOT_BE_EMPTY, msg);
    }

    public static CodedException errorTooShortName() {
        var msg = "Name must be at least 2 characters long";
        return new CodedException(TOO_SHORT_NAME, msg);
    }

    public static CodedException errorTooLongName() {
        var msg = "Name too long (max 20 chars)";
        return new CodedException(TOO_LONG_NAME, msg);
    }

    public static CodedException errorEmptyInterests() {
        var msg = "Interests cannot be empty";
        return new CodedException(INTERESTS_CANNOT_BE_EMPTY, msg);
    }

    public static CodedException errorInterestsTooLageText() {
        var msg = "Interests text too large";
        return new CodedException(INTERESTS_TOO_LAGE_TEXT, msg);
    }

    public static CodedException errorInvalidGender() {
        var msg = "invalid gender";
        return new CodedException(GENDER_INVALID, msg);
    }
}
