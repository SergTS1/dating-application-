package com.date.datingapp.adapter.repository.outbox;

import com.date.datingapp.adapter.repository.outbox.converter.OutboxConverter;
import com.date.datingapp.adapter.repository.outbox.model.OutboxEventDbModel;
import com.date.datingapp.boundary.model.event.OutboxEvent;
import com.date.datingapp.boundary.model.event.OutboxEventStatus;
import com.date.datingapp.boundary.repository.OutboxRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<OutboxEvent> findPendingEvents() {
        var query = Query.query(Criteria.where("status").is(OutboxEventStatus.PENDING));

        return mongoTemplate.find(query, OutboxEventDbModel.class)
                .stream()
                .map(OutboxConverter::toDomain)
                .toList();
    }

    @Override
    public void markAsPublished(String eventId) {
        if (eventId == null || eventId.isEmpty()) {
            throw OutboxRepositoryError.errEventIdIsRequired();
        }

        var query = Query.query(Criteria.where("id").is(eventId));
        var update = new Update().set("status", OutboxEventStatus.PUBLISHED);

        mongoTemplate.updateFirst(query, update, OutboxEventDbModel.class);
    }
}
