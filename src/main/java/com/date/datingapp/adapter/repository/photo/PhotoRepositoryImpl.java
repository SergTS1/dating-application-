package com.date.datingapp.adapter.repository.photo;

import com.date.datingapp.adapter.repository.photo.converter.PhotoConverter;
import com.date.datingapp.adapter.repository.photo.model.PhotoDbModel;
import com.date.datingapp.boundary.repository.PhotoRepository;
import com.date.datingapp.domain.entity.user.photo.Photo;
import com.date.datingapp.domain.entity.user.photo.PhotoId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class PhotoRepositoryImpl implements PhotoRepository {

    private final MongoTemplate mongoTemplate;

    public PhotoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Photo> getPhotoById(PhotoId id) {
        if (id == null) {
            throw PhotoRepositoryError.errPhotoIdIsRequired();
        }

        var photoDbModel = mongoTemplate.findById(id.toString(), PhotoDbModel.class);

        return Optional.ofNullable(photoDbModel).map(PhotoConverter::toEntity);
    }

    public void save(final Photo photo) {
        if (photo == null) {
            throw PhotoRepositoryError.errPhotoIsRequired();
        }

        var photoDbModel = PhotoConverter.toDbModel(photo);
        mongoTemplate.save(photoDbModel);
    }
}
