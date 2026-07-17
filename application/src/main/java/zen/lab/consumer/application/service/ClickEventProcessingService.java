/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zen.lab.consumer.application.port.in.MessageConsumerUseCase;
import zen.lab.consumer.application.port.out.ClickEventStore;
import zen.lab.consumer.domain.events.ClickEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClickEventProcessingService implements MessageConsumerUseCase {

    private final ClickEventStore clickEventStore;

    @Override
    public void process(ClickEvent event) {
        log.info(">>> Processing event: {}", event);
        clickEventStore.store(event);
    }
}
