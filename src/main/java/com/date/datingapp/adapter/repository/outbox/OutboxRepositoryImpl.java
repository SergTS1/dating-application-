package com.date.datingapp.adapter.repository.outbox;


import com.date.datingapp.adapter.repository.outbox.converter.OutboxConverter;
import com.date.datingapp.boundary.model.event.OutboxEvent;
import com.date.datingapp.boundary.repository.OutboxRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OutboxRepositoryImpl implements OutboxRepository {

    private final MongoTemplate mongoTemplate;

    public OutboxRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(OutboxEvent event) {
        if (event == null) {
            throw OutboxRepositoryError.errOutboxEventIsRequired();
        }

        var outboxEventDbModel = OutboxConverter.toDbModel(event);
        mongoTemplate.save(outboxEventDbModel);
    }
}
