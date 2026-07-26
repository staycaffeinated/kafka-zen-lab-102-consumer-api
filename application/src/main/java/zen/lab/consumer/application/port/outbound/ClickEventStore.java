/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.port.outbound;

import zen.lab.consumer.domain.events.ClickEvent;

public interface ClickEventStore {
    void store(ClickEvent event);
}
