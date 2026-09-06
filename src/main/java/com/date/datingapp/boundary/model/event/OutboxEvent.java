package com.date.datingapp.boundary.model.event;

import java.time.Instant;


public record OutboxEvent(String id,
                          String aggregateId,
                          OutboxEventType eventType,
                          String payload,
                          OutboxEventStatus status,
                          Instant createdAt
) {
}
