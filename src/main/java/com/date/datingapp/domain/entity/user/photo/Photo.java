package com.date.datingapp.domain.entity.user.photo;

public class Photo {

    private final PhotoId id;
    private final PhotoUrl url;
    private boolean verified;

    private Photo(PhotoId id, PhotoUrl url, boolean verified) {
        this.id = PhotoId.generate();
        this.url = url;
        this.verified = verified;
    }

    public static Photo upload(String url) {
        return new Photo(
                PhotoId.generate(),
                new PhotoUrl(url),
                false);
    }

    public void markVerified() {
        this.verified = true;
    }

    public PhotoId getId() {
        return id;
    }

    public boolean isVerified() {
        return verified;
    }
}
