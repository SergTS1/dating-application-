package com.date.datingapp.boundary.repository;

import com.date.datingapp.boundary.model.event.OutboxEvent;

import java.util.List;

public interface OutboxRepository {

    void save(OutboxEvent event);

    List<OutboxEvent> findPendingEvents();

    void markAsPublished(String eventId);
}
