package com.date.datingapp.domain.entity.user.photo;

import com.date.datingapp.domain.entity.user.enums.VerifiedStatus;

import static com.date.datingapp.domain.entity.user.enums.VerifiedStatus.ACTIVE;


public class Photo {

    private final PhotoId id;
    private final PhotoUrl url;
    private VerifiedStatus status;

    Photo(PhotoId id, PhotoUrl url, VerifiedStatus status) {
        this.id = id;
        this.url = url;
        this.status = status;
    }

    public static Photo upload(String url) {
        return new Photo(
                PhotoId.generate(),
                new PhotoUrl(url),
                //TODO: set status to INACTIVE later, it will be new logic
                ACTIVE);
    }

    public static Photo restore(
            PhotoId id,
            PhotoUrl url,
            VerifiedStatus status
    ) {
        return new Photo(id, url, status);
    }

    public void markVerified() {
        this.status = ACTIVE;
    }

    public PhotoId getId() {
        return id;
    }

    public PhotoUrl getUrl() {
        return url;
    }

    public VerifiedStatus getStatus() {
        return status;
    }
}
