package com.date.datingapp.adapter.controller.http.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Schema(description = "Response containing user details")
public class GetUserResponse {

    @Schema(description = "User data")
    @JsonProperty("data")
    public UserData data;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "User data")
    public static class UserData {

        @Schema(description = "User UUID", example = "550e8400-e29b-41d4-a716-446655440000")
        @JsonProperty("uuid")
        UUID uuid;

        @Schema(description = "User attributes")
        @JsonProperty("attributes")
        Attributes attributes;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "User attributes")
    public static class Attributes {

        @Schema(description = "User UUID", example = "550e8400-e29b-41d4-a716-446655440000")
        @JsonProperty("uuid")
        UUID uuid;

        @Schema(description = "User's photo")
        @JsonProperty("photo")
        String photo;

        @Schema(description = "User email")
        @JsonProperty("email")
        String email;

        @Schema(description = "User's name", example = "John")
        @JsonProperty("name")
        String name;

        @Schema(description = "User's gender", example = "Male")
        @JsonProperty("gender")
        String gender;

        @Schema(description = "User's interests", example = "soccer")
        @JsonProperty("interests")
        String interests;

        @Schema(description = "User's verification status", example = "VERIFIED")
        @JsonProperty("verification status")
        String verificationStatus;

        @Schema(description = "User's premium status ", example = "PREMIUM")
        @JsonProperty("premium status")
        String userPremiumStatus;
    }
}
