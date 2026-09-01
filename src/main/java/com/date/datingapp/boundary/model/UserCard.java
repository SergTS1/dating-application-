package com.date.datingapp.boundary.model;

import com.date.datingapp.domain.entity.user.enums.Gender;

import java.util.UUID;

public record UserCard(

        UUID userId,
        String name,
        Gender gender,
        String interests,
        PhotoCard photo
) {
}
