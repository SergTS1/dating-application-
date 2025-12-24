package com.date.datingapp.adapter.repository.user;

import com.date.datingapp.shared.exception.CodedException;

public class UserRepositoryError {

    public static final String ERR_USER_ID_IS_REQUIRED = "019a062f-001";
    public static final String ERR_USER_IS_REQUIRED = "019a062f-002";
    public static final String ERR_EMAIL_REQUIRED = "019a062f-003";
    public static final String ERR_USER_DB_MODEL_IS_REQUIRED = "019a062f-004";

    private UserRepositoryError() {
    }

    public static CodedException errUserIdIsRequired() {
        var msg = "User ID is required";
        return new CodedException(ERR_USER_ID_IS_REQUIRED, msg);
    }

    public static CodedException errUserIsRequired() {
        var msg = "User is required";
        return new CodedException(ERR_USER_IS_REQUIRED, msg);
    }

    public static CodedException errEmailIsRequired() {
        var msg = "Email is required";
        return new CodedException(ERR_EMAIL_REQUIRED, msg);
    }

    public static CodedException errUserDbModelIsRequired() {
        var msg = "User db model is required";
        return new CodedException(ERR_USER_DB_MODEL_IS_REQUIRED, msg);
    }
}
