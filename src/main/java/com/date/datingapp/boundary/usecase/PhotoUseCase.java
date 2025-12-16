package com.date.datingapp.boundary.usecase;


import com.date.datingapp.domain.entity.user.photo.PhotoId;

public interface PhotoUseCase {

    PhotoId getPhotoById(Long id);
}
