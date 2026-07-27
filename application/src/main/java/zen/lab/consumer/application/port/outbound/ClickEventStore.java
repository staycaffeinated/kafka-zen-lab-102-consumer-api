/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.port.outbound;

import zen.lab.consumer.domain.events.ClickEvent;

/**
 * Outbound port for persisting {@link ClickEvent} instances.
 *
 * <p>This is the driven port in the hexagonal architecture. The application service
 * ({@code ClickEventProcessingService}) calls {@link #store(ClickEvent)} without
 * knowing how or where the event is persisted. The current implementation is
 * {@code InMemoryClickEventStore}, which is a no-op stub intended for development.
 * Replace it with a real implementation (e.g., a database or another Kafka topic)
 * without modifying any application or domain code.
 */
public interface ClickEventStore {

    /**
     * Stores the given click event.
     *
     * <p>Implementations should be idempotent where possible, because the calling
     * service may invoke this method more than once for the same event if the
     * Kafka consumer retries a message.
     *
     * @param event the domain event to store; never null
     */
    void store(ClickEvent event);
}
