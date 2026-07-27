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
        var event = new ProductViewedEvent("evt-1", null, null, null, null, null, null, null);

        service.process(event);

        verify(clickEventStore).store(event);
        verifyNoMoreInteractions(clickEventStore);
    }
}
