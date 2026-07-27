package zen.lab.consumer.domain.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ProductAddedToCartEventTest {

    @Test
    void toStringMustNotRevealPersonallyIdentifiableInformation() {
        var event = ProductAddedToCartEvent.builder()
                .eventId("event-123")
                .eventType("product.added.to.cart")
                .productId("product-123")
                .sessionId("session-123")
                .correlationId("correlation-123")
                .userId("user-123")
                .metadata(Map.of())
                .timestamp(Instant.EPOCH)
                .build();

        assertThat(event.toString())
                .doesNotContain("user-123")
                .doesNotContain("session-123")
                .doesNotContain("correlation-123");
    }

    @Test
    void builderCopiesFieldsCorrectly() {

        Map<String, Serializable> someMetadata = Map.of("someKey", "someValue");

        ProductAddedToCartEvent event = ProductAddedToCartEvent.builder()
                .eventId("event-123")
                .eventType("product.added.to.cart")
                .productId("product-123")
                .sessionId("session-123")
                .correlationId("correlation-123")
                .userId("user-123")
                .metadata(someMetadata)
                .timestamp(Instant.EPOCH)
                .build();

        assertThat(event)
                .hasFieldOrPropertyWithValue("eventId", "event-123")
                .hasFieldOrPropertyWithValue("eventType", "product.added.to.cart")
                .hasFieldOrPropertyWithValue("productId", "product-123")
                .hasFieldOrPropertyWithValue("sessionId", "session-123")
                .hasFieldOrPropertyWithValue("correlationId", "correlation-123")
                .hasFieldOrPropertyWithValue("userId", "user-123")
                .hasFieldOrPropertyWithValue("metadata", someMetadata)
                .hasFieldOrPropertyWithValue("timestamp", Instant.EPOCH);
    }
}
