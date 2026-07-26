/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zen.lab.consumer.application.port.outbound.ClickEventStore;
import zen.lab.consumer.domain.events.ProductViewedEvent;

@ExtendWith(MockitoExtension.class)
class ClickEventProcessingServiceTest {

    @Mock
    ClickEventStore clickEventStore;

    @InjectMocks
    ClickEventProcessingService service;

    @Test
    void process_delegatesToStore() {
        var event = ProductViewedEvent.builder().eventId("evt-1").build();

        service.process(event);

        verify(clickEventStore).store(event);
        verifyNoMoreInteractions(clickEventStore);
    }
}
