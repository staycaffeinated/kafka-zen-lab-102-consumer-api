/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.port.in;

import zen.lab.consumer.domain.events.ClickEvent;

public interface MessageConsumerUseCase {

    void process(ClickEvent event);
}
