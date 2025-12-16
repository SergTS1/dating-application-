package com.date.datingapp.boundary.usecase;

import com.date.datingapp.boundary.model.CreateUserParam;
import com.date.datingapp.domain.entity.user.User;
import com.date.datingapp.domain.entity.user.UserId;

import java.util.UUID;


public interface UserUseCase {

    UserId create(CreateUserParam params);

    User getUserByUUID(UUID userId);

    void deleteUserByUUID(UUID userId);
}
