package com.date.datingapp.adapter.kafka.outbox;

import com.date.datingapp.adapter.kafka.OutboxEventTopicResolver;
import com.date.datingapp.boundary.model.event.OutboxEvent;
import com.date.datingapp.boundary.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class OutboxPublisher {

    OutboxRepository outboxRepository;
    KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void publish() {

        var events = outboxRepository.findPendingEvents();

        for (var event : events) {
            publishEvent(event);
        }
    }

    private void publishEvent(OutboxEvent event) {
        var topic = OutboxEventTopicResolver.resolve(event.eventType());

        kafkaTemplate.send(
                        topic,
                        event.aggregateId(),
                        event.payload())
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        log.error(
                                "Failed to publish outbox event. eventId={}, eventType={}",
                                event.id(),
                                event.eventType(),
                                exception
                        );
                        return;
                    }
                        outboxRepository.markAsPublished(event.id());

                        log.info(
                                "Outbox event published successfully. eventId={}, eventType={}, topic={}",
                                event.id(),
                                event.eventType(),
                                topic
                        );
                    });
    }
}
