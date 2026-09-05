package com.date.datingapp.boundary.repository;

import com.date.datingapp.boundary.model.event.OutboxEvent;

public interface OutboxRepository {

    void save(OutboxEvent event);
}
