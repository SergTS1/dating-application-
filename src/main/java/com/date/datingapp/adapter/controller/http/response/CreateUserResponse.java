package com.date.datingapp.adapter.controller.http.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Response after creating a new user")
public class CreateUserResponse {

    @Schema(description = "User data")
    @JsonProperty("data")
    public UserData data;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(description = "User data wrapper")
    public static class UserData {


        @Schema(description = "User UUID")
        @JsonProperty("uuid")
        UUID uuid;
    }
}
