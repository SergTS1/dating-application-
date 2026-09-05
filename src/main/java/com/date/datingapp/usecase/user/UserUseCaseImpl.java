package com.date.datingapp.usecase.user;

import com.date.datingapp.adapter.storage.MinioStorage;
import com.date.datingapp.boundary.model.CreateUserParam;
import com.date.datingapp.boundary.model.event.OutboxEvent;
import com.date.datingapp.boundary.model.event.UserCreatedEvent;
import com.date.datingapp.boundary.repository.OutboxRepository;
import com.date.datingapp.boundary.repository.UserRepository;
import com.date.datingapp.boundary.usecase.UserUseCase;
import com.date.datingapp.domain.entity.user.*;
import com.date.datingapp.domain.entity.user.enums.Gender;
import com.date.datingapp.infra.logger.Logger;
import com.date.datingapp.infra.util.PageParam;
import com.date.datingapp.infra.util.PaginationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;
    private final OutboxRepository outboxRepository;
    private final MinioStorage minioStorage;
    private final Logger logger;
    private final UserUseCaseError userUseCaseError;
    private final ObjectMapper objectMapper;
    private static final String SUCCESSFULLY_REGISTERED = "User successfully registered. userId={}";
    private static final String USER_CREATED_EVENT = "USER_CREATED";

    public UserUseCaseImpl(
            UserRepository userRepository,
            OutboxRepository outboxRepository,
            Logger logger,
            UserUseCaseError userUseCaseError,
            MinioStorage minioStorage,
            ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.outboxRepository = outboxRepository;
        this.logger = logger;
        this.userUseCaseError = userUseCaseError;
        this.minioStorage = minioStorage;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public UserId create(CreateUserParam params) {
        if (params == null) {
            throw userUseCaseError.paramsAreRequired();
        }

        Email email = new Email(params.getEmail());
        PasswordHash password = new PasswordHash(params.getPassword());

        UserName name = new UserName(params.getName());
        Gender gender = Gender.from(params.getGender());
        Interests interests = new Interests(params.getInterests());

        Profile profile = new Profile(name, gender, interests);

        if (userRepository.existsByEmail(email)) {
            throw userUseCaseError.userAlreadyExists();
        }

        User user = User.register(
                email,
                password,
                profile
        );

        userRepository.save(user);
        createUserOutboxEvent(user);

        logger.info(SUCCESSFULLY_REGISTERED, user.getId().value());
        return user.getId();
    }

    private void createUserOutboxEvent(User user) {
        UserCreatedEvent event = new UserCreatedEvent(user.getId().value().toString());
        String payload;

        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize UserCreatedEvent", e);
        }

        OutboxEvent outboxEvent = new OutboxEvent(
                UUID.randomUUID().toString(),
                user.getId().value().toString(),
                USER_CREATED_EVENT,
                payload,
                Instant.now()
        );

        outboxRepository.save(outboxEvent);
    }

    @Override
    public User getUserByUUID(UUID userId) {
        Optional<User> result = userRepository.getUserByUUID(userId);

        if (result.isEmpty()) {
            throw userUseCaseError.userNotFound(userId);
        }

        return result.get();
    }

    @Override
    public void deleteUserByUUID(UUID userId) {
        userRepository.deleteUserByUUID(userId);
    }

    @Override
    public Page<User> getUserCards(PageParam pageParam) {
        Pageable pageable = PaginationUtil.getPageable(pageParam);

        return userRepository.getUsersForCards(pageable);
    }

    @Override
    public void uploadPhoto(UUID uuid, String fileName, String contentType, byte[] content) {
        User user = userRepository.getUserByUUID(uuid).orElseThrow(() -> userUseCaseError.userNotFound(uuid));

        String url = minioStorage.upload(uuid, fileName, contentType, content);
        user.uploadPhoto(url);
        userRepository.save(user);
    }

}
