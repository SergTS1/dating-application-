package com.date.datingapp.adapter.repository.photo;

import com.date.datingapp.shared.exception.CodedException;

public class PhotoRepositoryError {

    public static final String ERR_PHOTO_ID_IS_REQUIRED = "0199fc94-001";
    public static final String ERR_PHOTO_IS_REQUIRED = "0199fc94-002";

    private PhotoRepositoryError() {
    }

    public static CodedException errPhotoIdIsRequired() {
        var msg = "Photo ID is required";
        return new CodedException(ERR_PHOTO_ID_IS_REQUIRED, msg);
    }

    public static CodedException errPhotoIsRequired() {
        var msg = "Photo is required";
        return new CodedException(ERR_PHOTO_IS_REQUIRED, msg);
    }
}
