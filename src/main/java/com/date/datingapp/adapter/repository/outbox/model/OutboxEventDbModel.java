package com.date.datingapp.adapter.repository.outbox.model;

import com.date.datingapp.boundary.model.event.OutboxEventType;
import com.date.datingapp.boundary.model.event.OutboxEventStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "outbox_event")
@Getter
@Setter
public class OutboxEventDbModel {
    @Id
    private String id;
    private String aggregateId;
    private OutboxEventType eventType;
    private String payload;
    private OutboxEventStatus status;
    private Instant createdAt;
}
