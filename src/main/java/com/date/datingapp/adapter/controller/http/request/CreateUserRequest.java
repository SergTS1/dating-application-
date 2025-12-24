package com.date.datingapp.adapter.controller.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Schema(description = "Request to create a new user")
public class CreateUserRequest {

    @Schema(description = "User data")
    @JsonProperty("data")
    UserData data;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "User data wrapper")
    public static class UserData {

        @Schema(description = "User attributes")
        @JsonProperty("attributes")
        Attributes attributes;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "User attributes")
    public static class Attributes {

        @Schema(description = "User's name", example = "John Doe")
        @JsonProperty("name")
        String name;

        @Schema(description = "user email")
        @JsonProperty("email")
        String email;

        @Schema(description = "User's password", example = "lkrgni3k")
        @JsonProperty("password")
        String password;

        @Schema(description = "User's gender", example = "Male")
        @JsonProperty("gender")
        String gender;

        @Schema(description = "User's interests", example = "soccer")
        @JsonProperty("interests")
        String interests;
    }

}
