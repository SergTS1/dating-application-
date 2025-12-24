package com.date.datingapp.adapter.controller.http.convertor;


import com.date.datingapp.adapter.controller.http.response.CreateUserResponse;
import com.date.datingapp.adapter.controller.http.response.GetUserResponse;
import com.date.datingapp.domain.entity.user.User;
import com.date.datingapp.domain.entity.user.photo.Photo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserResponseMapper {

    public GetUserResponse toDto(User user) {
        if (user == null) {
            return null;
        }

        GetUserResponse.Attributes attributes = toGetUserResponse(user);

        GetUserResponse.UserData userData = new GetUserResponse.UserData();
        userData.setUuid(user.getId().value());
        userData.setAttributes(attributes);

        GetUserResponse response = new GetUserResponse();
        response.setData(userData);
        return response;
    }

    public GetUserResponse.Attributes toGetUserResponse(User user) {
        if (user == null) {
            return null;
        }

        GetUserResponse.Attributes attributes = new GetUserResponse.Attributes();
        attributes.setUuid(user.getId().value());
        attributes.setEmail(user.getEmail().value());
        attributes.setName(user.getProfile().name().value());
        attributes.setGender(user.getProfile().gender().name());
        attributes.setInterests(user.getProfile().interests().value());
        attributes.setVerificationStatus(user.getVerificationStatus().name());
        attributes.setUserPremiumStatus(user.getPremiumStatus().name());

        String photo = String.valueOf(user.getPhotos().stream()
                .findFirst()
                .map(Photo::getUrl)
                .orElse(null));
        attributes.setPhoto(photo);

        return attributes;
    }

    public CreateUserResponse toCreateDto(UUID userId) {
        if (userId == null) {
            return null;
        }

        CreateUserResponse.UserData userData = toCreateUserResponse(userId);
        CreateUserResponse response = new CreateUserResponse();
        response.setData(userData);
        return response;
    }

    public CreateUserResponse.UserData toCreateUserResponse(UUID userId) {
        if (userId == null) {
            return null;
        }

        CreateUserResponse.UserData userData = new CreateUserResponse.UserData();
        userData.setUuid(userId);
        return userData;
    }


}
