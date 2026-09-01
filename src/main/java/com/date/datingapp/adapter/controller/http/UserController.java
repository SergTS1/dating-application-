package com.date.datingapp.adapter.controller.http;


import com.date.datingapp.adapter.controller.http.convertor.UserRequestMapper;
import com.date.datingapp.adapter.controller.http.convertor.UserResponseMapper;
import com.date.datingapp.adapter.controller.http.request.CreateUserRequest;
import com.date.datingapp.adapter.controller.http.response.CreateUserResponse;
import com.date.datingapp.adapter.controller.http.response.GetUserResponse;
import com.date.datingapp.boundary.model.UserCard;
import com.date.datingapp.boundary.model.CreateUserParam;
import com.date.datingapp.boundary.usecase.UserUseCase;
import com.date.datingapp.domain.entity.user.User;
import com.date.datingapp.domain.entity.user.UserId;
import com.date.datingapp.infra.util.PageParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "APIs for users")
public class UserController {

    UserUseCase userUseCase;
    UserRequestMapper requestMapper;
    UserResponseMapper responseMapper;

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserParam params = requestMapper.toParam(request);
        UserId userId = userUseCase.create(params);
        CreateUserResponse getUserResponse = responseMapper.toCreateDto(userId.value());
        return ResponseEntity.ok(getUserResponse);
    }

    @Operation(summary = "Get user by UUID", description = "Retrieves user information by user UUID.")
    @GetMapping(path = "/{uuid}")
    public ResponseEntity<GetUserResponse> getUserByUUID(@PathVariable UUID uuid) {
        User user = userUseCase.getUserByUUID(uuid);
        GetUserResponse getUserResponse = responseMapper.toDto(user);
        return ResponseEntity.ok(getUserResponse);

    }

    @Operation(summary = "Delete user by id", description = "Deletes an existing user by id.")
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByUUID(@PathVariable UUID uuid) {
        userUseCase.deleteUserByUUID(uuid);
    }

    @Operation(summary = "Get user cards", description = "Retrieves a paginated list of user cards for swiping.")
    @GetMapping("/cards")
    public ResponseEntity<Page<UserCard>> getUserCards(@ParameterObject PageParam pageParam) {
        Page<User> response = userUseCase.getUserCards(pageParam);
        Page<UserCard> userCardPage = response.map(responseMapper::toUserCard);
        return ResponseEntity.ok(userCardPage);
    }

}
