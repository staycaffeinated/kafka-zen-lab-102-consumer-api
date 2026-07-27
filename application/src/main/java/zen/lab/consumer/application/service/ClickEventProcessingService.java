/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zen.lab.consumer.application.port.inbound.MessageConsumerUseCase;
import zen.lab.consumer.application.port.outbound.ClickEventStore;
import zen.lab.consumer.domain.events.ClickEvent;

/**
 * Application service that implements the {@link MessageConsumerUseCase} inbound port.
 *
 * <p>This is the sole application-layer service. Its only responsibility is to
 * delegate a received {@link ClickEvent} to the {@link ClickEventStore} outbound port.
 * Orchestration logic — such as event enrichment, routing to multiple stores, or
 * publishing secondary domain events — should be added here as the application grows.
 *
 * <p>The class is annotated with {@code @Service} to satisfy the ArchUnit rule that
 * all {@code @Service}-annotated classes reside in {@code application.service}.
 * Constructor injection is used (via Lombok {@code @RequiredArgsConstructor}) in
 * accordance with the no-field-injection ArchUnit rule.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClickEventProcessingService implements MessageConsumerUseCase {

    private final ClickEventStore clickEventStore;

    /**
     * {@inheritDoc}
     *
     * <p>Logs the event at INFO level, then delegates persistence to the injected
     * {@link ClickEventStore}. The Kafka adapter acknowledges the offset after this
     * method returns without exception.
     */
    @Override
    public void process(ClickEvent event) {
        log.info(">>> Processing event: {}", event);
        clickEventStore.store(event);
    }
}
