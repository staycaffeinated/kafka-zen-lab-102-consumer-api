/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.port.inbound;

import zen.lab.consumer.domain.events.ClickEvent;

/**
 * Inbound port for processing a single {@link ClickEvent}.
 *
 * <p>This interface is the driving port in the hexagonal architecture. The Kafka
 * adapter ({@code ClickEventMessageConsumer}) calls {@link #process(ClickEvent)} after
 * mapping an incoming Kafka message to a domain event. The port is implemented by
 * {@code ClickEventProcessingService} in the application service layer.
 *
 * <p>Implementations must be idempotent where possible, because Kafka's at-least-once
 * delivery guarantee means the same event may be delivered more than once in failure
 * scenarios.
 */
public interface MessageConsumerUseCase {

    /**
     * Processes a domain click event.
     *
     * <p>After this method returns normally the calling adapter will acknowledge the
     * Kafka offset. If this method throws an exception, the error handler will retry
     * the message according to the configured back-off policy before routing it to
     * the dead-letter queue.
     *
     * @param event the domain event to process; never null
     */
    void process(ClickEvent event);
}
