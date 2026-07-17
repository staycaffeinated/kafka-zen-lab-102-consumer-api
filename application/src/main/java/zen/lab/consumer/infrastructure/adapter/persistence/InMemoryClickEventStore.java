/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.adapter.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zen.lab.consumer.application.port.out.ClickEventStore;
import zen.lab.consumer.domain.events.ClickEvent;

@Component
@Slf4j
public class InMemoryClickEventStore implements ClickEventStore {

    @Override
    public void store(ClickEvent event) {
        log.debug("Stored event: {}", event.getEventId());
    }
}
