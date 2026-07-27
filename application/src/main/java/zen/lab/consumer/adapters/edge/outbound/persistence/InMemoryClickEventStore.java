/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.edge.outbound.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zen.lab.consumer.application.port.outbound.ClickEventStore;
import zen.lab.consumer.domain.events.ClickEvent;

/**
 * Development stub implementation of {@link ClickEventStore}.
 *
 * <p>This adapter discards every event after logging its ID at DEBUG level.
 * It exists so that the application starts and processes messages during local
 * development without requiring a real persistence backend.
 *
 * <p><strong>Do not use in production.</strong> Replace this with a concrete
 * implementation that writes to a database, a downstream Kafka topic, or another
 * durable store. Register the replacement as a Spring bean and remove (or qualify)
 * this stub to resolve any ambiguity.
 */
@Component
@Slf4j
public class InMemoryClickEventStore implements ClickEventStore {

    /**
     * {@inheritDoc}
     *
     * <p>This stub implementation logs the event ID at DEBUG level and discards the
     * event. No data is persisted.
     */
    @Override
    public void store(ClickEvent event) {
        log.debug("Stored event: {}", event.eventId());
    }
}
