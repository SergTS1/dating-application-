package com.date.datingapp.domain.entity.user;

import com.date.datingapp.domain.entity.user.enums.Gender;
import com.date.datingapp.domain.entity.user.photo.PhotoId;
import com.date.datingapp.domain.entity.user.photo.PhotoUrl;
import com.date.datingapp.shared.exception.CodedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    @Test
    void shouldRegisterUserWithProfile() {
        Profile profile = new Profile(new UserName( "Alice"), Gender.FEMALE, new Interests("art"));
        User user = User.register(new Email ("alice@mail.com"), new PasswordHash( "hash123"), profile);
        assertEquals("alice@mail.com", user.getEmail().value());
        assertFalse(user.isVerified());
    }

    @Test
    void shouldVerifyUserAfterPhotoApproval() {
        User user = User.register(new Email ("bob@mail.com"),new PasswordHash( "hash"), new Profile(new UserName("Bob"), Gender.MALE, new Interests("travel")));
        user.uploadPhoto("https://photos.com/1");
        PhotoId photoId = user.getPhotos().get(0).getId();
        user.verifyPhoto(photoId);
        assertTrue(user.isVerified());
    }

    @Test
    void shouldNotActivatePremiumBeforeVerification() {
        User user = User.register(new Email ("eve@mail.com"), new PasswordHash("hash"), new Profile(new UserName("Eve"), Gender.FEMALE, new Interests("design")));
        assertThrows(CodedException.class, user::activatePremium);
    }

    @Test
    void shouldActivatePremiumAfterVerification() {
        User user = User.register(new Email ("john@mail.com"), new PasswordHash("hash"), new Profile(new UserName("John"), Gender.MALE, new Interests("tech")));
        user.uploadPhoto("https://photo.com/1");
        PhotoId photoId = user.getPhotos().get(0).getId();
        user.verifyPhoto(photoId);
        user.activatePremium();
        assertTrue(user.isPremium());
    }

    @Test
    void shouldUploadPhoto() {
        var user = User.register(new Email ("bob@mail.com"), new PasswordHash("hash"), new Profile(new UserName("Bob"), Gender.MALE, new Interests("sports")));
        user.uploadPhoto("https://example.com/bob.jpg");
        assertEquals(1, user.getPhotos().size());
    }

    @Test
    void shouldCreateValidPhotoUrl() {
        PhotoUrl url = new PhotoUrl("https://example.com/image.png");
        assertEquals("https://example.com/image.png", url.value());
    }

    @Test
    void shouldRejectBlankUrl() {
        assertThrows(CodedException.class,
                () -> new PhotoUrl("   "));
    }

    @Test
    void shouldRejectMalformedUrl() {
        assertThrows(CodedException.class,
                () -> new PhotoUrl("qwerty"));
    }

    @Test
    void shouldRejectUnsupportedProtocol() {
        assertThrows(CodedException.class,
                () -> new PhotoUrl("ftp://example.com"));
    }
}

