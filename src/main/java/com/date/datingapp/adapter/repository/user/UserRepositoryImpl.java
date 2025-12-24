package com.date.datingapp.adapter.repository.user;

import com.date.datingapp.adapter.repository.user.converter.UserConverter;
import com.date.datingapp.adapter.repository.user.model.UserDbModel;
import com.date.datingapp.boundary.repository.UserRepository;
import com.date.datingapp.domain.entity.user.Email;
import com.date.datingapp.domain.entity.user.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<User> getUserByUUID(UUID uuid) {
        if (uuid == null) {
            throw UserRepositoryError.errUserIdIsRequired();
        }

        var userDbModel = mongoTemplate.findById(uuid.toString(), UserDbModel.class);

        return Optional.ofNullable(userDbModel).map(UserConverter::toEntity);
    }

    @Override
    public void save(final User user) {
        if (user == null) {
            throw UserRepositoryError.errUserIsRequired();
        }

        var userDbModel = UserConverter.toDbModel(user);
        mongoTemplate.save(userDbModel);
    }

    @Override
    public boolean existsByEmail(Email email) {
        if (email == null) {
            throw UserRepositoryError.errEmailIsRequired();
        }

        Query query = Query.query(
                Criteria.where("email").is(email.value())
        );

        return mongoTemplate.exists(query, UserDbModel.class);
    }

    @Override
    public void deleteUserByUUID(UUID uuid) {
        if (uuid == null) {
            throw UserRepositoryError.errUserIdIsRequired();
        }

        Query query = Query.query(
                Criteria.where("id").is(uuid.toString())
        );

        mongoTemplate.remove(query, UserDbModel.class);
    }
}
