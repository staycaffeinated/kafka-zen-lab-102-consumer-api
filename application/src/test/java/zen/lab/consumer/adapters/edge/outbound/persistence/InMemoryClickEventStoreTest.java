/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.edge.outbound.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import zen.lab.consumer.domain.events.ProductViewedEvent;

class InMemoryClickEventStoreTest {

    InMemoryClickEventStore store = new InMemoryClickEventStore();

    @Test
    void store_doesNotThrow() {
        var event = new ProductViewedEvent("evt-1", "PRODUCT_VIEWED", null, null, null, null, null, null);
        assertDoesNotThrow(() -> store.store(event));
    }

    @Test
    void store_handlesNullEventId() {
        var event = new ProductViewedEvent(null, "PRODUCT_VIEWED", null, null, null, null, null, null);
        assertDoesNotThrow(() -> store.store(event));
    }
}
