package com.date.datingapp.adapter.repository.outbox.converter;

import com.date.datingapp.adapter.repository.outbox.model.OutboxEventDbModel;
import com.date.datingapp.boundary.model.event.OutboxEvent;

public class OutboxConverter {

    private OutboxConverter() {
    }

    public static OutboxEventDbModel toDbModel(OutboxEvent event) {
        OutboxEventDbModel model = new OutboxEventDbModel();
        model.setId(event.id());
        model.setAggregateId(event.aggregateId());
        model.setEventType(event.eventType());
        model.setPayload(event.payload());
        model.setCreatedAt(event.createdAt());

        return model;
    }

    public static OutboxEvent toDomain(OutboxEventDbModel model) {

        return new OutboxEvent(
                model.getId(),
                model.getAggregateId(),
                model.getEventType(),
                model.getPayload(),
                model.getCreatedAt()
        );
    }
}
