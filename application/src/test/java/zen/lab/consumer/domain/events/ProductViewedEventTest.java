package zen.lab.consumer.domain.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.time.Instant;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

class ProductViewedEventTest {

    @Nested
    class NullGuards {

        @Test
        void throwsWhenEventIdIsNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> validBuilder().eventId(null).build())
                    .withMessage("eventId must not be null");
        }

        @Test
        void throwsWhenEventTypeIsNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> validBuilder().eventType(null).build())
                    .withMessage("eventType must not be null");
        }

        @Test
        void throwsWhenTimestampIsNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> validBuilder().timestamp(null).build())
                    .withMessage("timestamp must not be null");
        }

        @Test
        void throwsWhenUserIdIsNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> validBuilder().userId(null).build())
                    .withMessage("userId must not be null");
        }

        @Test
        void throwsWhenSessionIdIsNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> validBuilder().sessionId(null).build())
                    .withMessage("sessionId must not be null");
        }

        private ProductViewedEvent.Builder validBuilder() {
            return ProductViewedEvent.builder()
                    .eventId("event-123")
                    .eventType("product.viewed")
                    .timestamp(Instant.EPOCH)
                    .userId("usr-1")
                    .sessionId("sess-1");
        }
    }

    @Test
    void toStringMustNotRevealPersonallyIdentifiableInformation() {
        var product = new Product(
                "product-123",
                null,
                "Product Name",
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                null,
                null,
                null,
                null,
                null);
        var context = new Context("home-page", null, "web", null, null);

        var event = ProductViewedEvent.builder()
                .eventId("event-123")
                .eventType("PRODUCT_VIEWED")
                .timestamp(Instant.parse("2026-01-01T00:00:00Z"))
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
