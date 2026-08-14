/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.port.outbound.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import zen.lab.consumer.domain.events.ProductViewedEvent;

class InMemoryClickEventStoreTest {

    InMemoryClickEventStore store = new InMemoryClickEventStore();

    @Test
    void store_doesNotThrow() {
        var event =
                new ProductViewedEvent("evt-1", "PRODUCT_VIEWED", Instant.EPOCH, "usr-1", "sess-1", null, null, null);
        assertDoesNotThrow(() -> store.store(event));
    }
}
