package com.date.datingapp.boundary.repository;

import com.date.datingapp.domain.entity.user.Email;
import com.date.datingapp.domain.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> getUserByUUID(UUID uuid);

    void save(User user);

    boolean existsByEmail(Email email);

    void deleteUserByUUID(UUID uuid);

    Page<User> getUsersForCards(Pageable pageable);
}
