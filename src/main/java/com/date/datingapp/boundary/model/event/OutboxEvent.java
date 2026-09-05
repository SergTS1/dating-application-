package com.date.datingapp.boundary.model.event;

import java.time.Instant;


public record OutboxEvent(String id,
                          String aggregateId,
                          String eventType,
                          String payload,
                          Instant createdAt
) {
}
