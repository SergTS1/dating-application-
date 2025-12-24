package com.date.datingapp.boundary.repository;

import com.date.datingapp.domain.entity.user.photo.Photo;
import com.date.datingapp.domain.entity.user.photo.PhotoId;

import java.util.Optional;

public interface PhotoRepository {

    Optional<Photo> getPhotoById(PhotoId id);

    void save(Photo photo);
}
