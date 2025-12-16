package com.date.datingapp.adapter.repository.user.converter;

import com.date.datingapp.adapter.repository.photo.converter.PhotoConverter;
import com.date.datingapp.adapter.repository.photo.model.PhotoDbModel;
import com.date.datingapp.adapter.repository.user.UserRepositoryError;
import com.date.datingapp.adapter.repository.user.model.UserDbModel;
import com.date.datingapp.domain.entity.user.*;
import com.date.datingapp.domain.entity.user.enums.Gender;
import com.date.datingapp.domain.entity.user.enums.UserPremiumStatus;
import com.date.datingapp.domain.entity.user.enums.UserVerificationStatus;

import java.util.List;
import java.util.UUID;

public final class UserConverter {

    private UserConverter() {
    }

    public static UserDbModel toDbModel(User entity) {
        List<PhotoDbModel> photoDbModel = entity.getPhotos().stream().map(PhotoConverter::toDbModel).toList();

        var dbModel = new UserDbModel();
        dbModel.setId(entity.getId().value().toString());
        dbModel.setEmail(entity.getEmail().value());
        dbModel.setPassword(entity.getPassword().value());
        dbModel.setName(entity.getProfile().name().value());
        dbModel.setGender(entity.getProfile().gender().name());
        dbModel.setInterests(entity.getProfile().interests().value());
        dbModel.setPhotos(photoDbModel);
        dbModel.setVerificationStatus(entity.getVerificationStatus().name());
        dbModel.setPremiumStatus(entity.getPremiumStatus().name());
        return dbModel;
    }

    public static User toEntity(UserDbModel dbModel) {

        if (dbModel == null) {
            throw UserRepositoryError.errUserDbModelIsRequired();
        }

        return User.restore(
                new UserId(UUID.fromString(dbModel.getId())),
                new Email(dbModel.getEmail()),
                new PasswordHash(dbModel.getPassword()),
                new Profile(
                        new UserName(dbModel.getName()),
                        Gender.from(dbModel.getGender()),
                        new Interests(dbModel.getInterests())
                ),
                dbModel.getPhotos().stream()
                        .map(PhotoConverter::toEntity)
                        .toList(),
                UserVerificationStatus.valueOf(dbModel.getVerificationStatus()),
                UserPremiumStatus.valueOf(dbModel.getPremiumStatus())
        );
    }

}
