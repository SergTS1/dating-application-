package com.date.datingapp.adapter.repository.photo.converter;

import com.date.datingapp.adapter.repository.photo.model.PhotoDbModel;
import com.date.datingapp.domain.entity.user.enums.VerifiedStatus;
import com.date.datingapp.domain.entity.user.photo.Photo;
import com.date.datingapp.domain.entity.user.photo.PhotoId;
import com.date.datingapp.domain.entity.user.photo.PhotoUrl;

import java.util.UUID;

public final class PhotoConverter {

    private PhotoConverter() {
    }

    public static PhotoDbModel toDbModel(Photo photo) {
        var photoDbModel = new PhotoDbModel();
        photoDbModel.setId(photo.getId().toString());
        photoDbModel.setUrl(photo.getUrl().value());
        photoDbModel.setStatus(photo.getStatus().name());
        return photoDbModel;
    }

    public static Photo toEntity(PhotoDbModel photoDbModel) {
        return Photo.restore(
                new PhotoId(UUID.fromString(photoDbModel.getId())),
                new PhotoUrl(photoDbModel.getUrl()),
                parseStatus(photoDbModel.getStatus())
        );
    }

    private static VerifiedStatus parseStatus(String rawStatus) {
        if (rawStatus == null) {
            return VerifiedStatus.INACTIVE;
        }
        try {
            return VerifiedStatus.valueOf(rawStatus);
        } catch (IllegalArgumentException ex) {
            return VerifiedStatus.INACTIVE;
        }
    }
}
