package com.date.datingapp.domain.usecase.photo;

import com.date.datingapp.boundary.usecase.PhotoUseCase;
import com.date.datingapp.domain.entity.user.photo.PhotoId;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
public class PhotoUseCaseImpl implements PhotoUseCase {
    @Override
    public PhotoId getPhotoById(Long id) {
        throw new NotImplementedException("PhotoUseCase.getPhotoById()");
    }
}
