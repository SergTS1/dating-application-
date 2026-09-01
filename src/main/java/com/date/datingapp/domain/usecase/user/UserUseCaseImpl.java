package com.date.datingapp.domain.usecase.user;

import com.date.datingapp.boundary.model.CreateUserParam;
import com.date.datingapp.boundary.repository.UserRepository;
import com.date.datingapp.boundary.usecase.UserUseCase;
import com.date.datingapp.domain.entity.user.*;
import com.date.datingapp.domain.entity.user.enums.Gender;
import com.date.datingapp.infra.logger.Logger;
import com.date.datingapp.infra.util.PageParam;
import com.date.datingapp.infra.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;
    private final Logger logger;
    private final UserUseCaseError userUseCaseError;
    private static final String SUCCESSFULLY_REGISTERED = "User successfully registered. userId={}";

    public UserUseCaseImpl(
            UserRepository userRepository,
            Logger logger,
            UserUseCaseError userUseCaseError) {
        this.userRepository = userRepository;
        this.logger = logger;
        this.userUseCaseError = userUseCaseError;
    }

    @Override
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
        logger.info(SUCCESSFULLY_REGISTERED, user.getId().value());
        return user.getId();
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

}
