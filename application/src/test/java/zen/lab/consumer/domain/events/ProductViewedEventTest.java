package zen.lab.consumer.domain.events;

import org.junit.jupiter.api.Test;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class ProductViewedEventTest {

    @Test
    void toStringMustNotRevealPersonallyIdentifiableInformation() {
        var product = new Product("product-123", null, "Product Name", null, null, null, null,
                null, null, true, null, null, null, null, null);
        var context = new Context("home-page", null, "web", null, null);

        var event = ProductViewedEvent.builder()
                .eventId("event-123")
                .eventType("PRODUCT_VIEWED")
                .timestamp(Instant.now())
                .userId("user-123")
                .sessionId("session-123")
                .correlationId("correlation-123")
                .product(product)
                .context(context)
                .build();

        assertThat(event.toString())
                .doesNotContain("user-123")
                .doesNotContain("session-123")
                .doesNotContain("correlation-123");
    }

}
