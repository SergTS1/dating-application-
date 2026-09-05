package com.date.datingapp.adapter.repository.outbox.model;

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
    private String eventType;
    private String payload;
    private Instant createdAt;
}
