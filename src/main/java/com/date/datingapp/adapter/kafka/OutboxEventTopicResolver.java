package com.date.datingapp.adapter.kafka;

import com.date.datingapp.boundary.model.event.OutboxEventType;


public final class OutboxEventTopicResolver {

    private OutboxEventTopicResolver() {
    }

    public static String resolve(OutboxEventType event) {
        return switch (event) {
            case USER_CREATED -> KafkaTopics.USER_CREATED;
            case USER_DELETED -> KafkaTopics.USER_DELETED;
        };
    }
}
