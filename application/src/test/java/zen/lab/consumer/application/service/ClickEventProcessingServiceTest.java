/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zen.lab.consumer.application.port.outbound.ClickEventStore;
import zen.lab.consumer.domain.events.ProductViewedEvent;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

@ExtendWith(MockitoExtension.class)
class ClickEventProcessingServiceTest {

    @Mock
    ClickEventStore clickEventStore;

    @InjectMocks
    ClickEventProcessingService service;

    @Test
    void process_delegatesToStore() {
        var product = Product.builder().productId("prod-1").name("Test Product").build();
        var context = Context.builder().pageId("page-1").source("search").build();
        var event = ProductViewedEvent.builder()
                .eventId("evt-1")
                .eventType("product.viewed")
                .timestamp(Instant.EPOCH)
                .userId("usr-1")
                .sessionId("sess-1")
                .product(product)
                .context(context)
                .build();

        service.process(event);

        verify(clickEventStore).store(event);
        verifyNoMoreInteractions(clickEventStore);
    }
}
