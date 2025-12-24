package com.date.datingapp.domain.entity.user;

import com.date.datingapp.domain.entity.user.enums.UserPremiumStatus;
import com.date.datingapp.domain.entity.user.enums.UserVerificationStatus;
import com.date.datingapp.domain.entity.user.enums.VerifiedStatus;
import com.date.datingapp.domain.entity.user.photo.Photo;
import com.date.datingapp.domain.entity.user.photo.PhotoId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private final UserId id;
    private final Email email;
    private final PasswordHash password;
    private final Profile profile;
    private final List<Photo> photos;
    private UserVerificationStatus verificationStatus;
    private UserPremiumStatus premiumStatus;

    private User(UserId id, Email email, PasswordHash password, Profile profile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.photos = new ArrayList<>();
        this.verificationStatus = UserVerificationStatus.NOT_VERIFIED;
        this.premiumStatus = UserPremiumStatus.FREE;
    }

    public static User restore(
            UserId id,
            Email email,
            PasswordHash password,
            Profile profile,
            List<Photo> photos,
            UserVerificationStatus verificationStatus,
            UserPremiumStatus premiumStatus
    ) {
        User user = new User(id, email, password, profile);
        user.photos.addAll(photos);
        user.verificationStatus = verificationStatus;
        user.premiumStatus = premiumStatus;
        return user;
    }

    public static User register(Email email, PasswordHash password, Profile profile) {
        return new User(UserId.generate(), email, password, profile);
    }

    public void uploadPhoto(String url) {
        photos.add(Photo.upload(url));
    }

    public void verifyPhoto(PhotoId photoId) {
        photos.stream()
                .filter(p -> p.getId().equals(photoId))
                .findFirst()
                .ifPresent(Photo::markVerified);
        if (photos.stream().anyMatch(p -> p.getStatus() == VerifiedStatus.ACTIVE)) {
            this.verificationStatus = UserVerificationStatus.VERIFIED;
        }
    }

    public void activatePremium() {
        if (verificationStatus == UserVerificationStatus.NOT_VERIFIED) {
            throw UserError.errCannotBeActivated();
        }
        this.premiumStatus = UserPremiumStatus.PREMIUM;
    }

    public boolean isVerified() {
        return verificationStatus == UserVerificationStatus.VERIFIED;
    }

    public boolean isPremium() {
        return premiumStatus == UserPremiumStatus.PREMIUM;
    }

    public UserVerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public UserPremiumStatus getPremiumStatus() {
        return premiumStatus;
    }

    public UserId getId() {
        return id;
    }

    public List<Photo> getPhotos() {
        return Collections.unmodifiableList(photos);
    }

    public Email getEmail() {
        return email;
    }

    public PasswordHash getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }
}
