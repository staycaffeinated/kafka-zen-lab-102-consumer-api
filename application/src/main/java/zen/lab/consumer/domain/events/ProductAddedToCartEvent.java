package zen.lab.consumer.domain.events;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

/**
 * Domain event that records a user adding a product to their shopping cart.
 *
 * <p>This record is an immutable value object in the domain layer. Unlike
 * {@link ProductViewedEvent}, it does not carry a full product snapshot; instead it
 * carries only the {@code productId} and an open-ended {@code metadata} map for
 * upstream-defined key-value pairs (e.g., quantity, coupon code).
 *
 * <p>Use {@link #builder()} to construct instances:
 * <pre>{@code
 * ProductAddedToCartEvent event = ProductAddedToCartEvent.builder()
 *     .eventId("evt-002")
 *     .eventType("cart.item.added")
 *     .timestamp(Instant.now())
 *     .userId("usr-42")
 *     .sessionId("sess-xyz")
 *     .correlationId("corr-abc")
 *     .productId("prod-999")
 *     .metadata(Map.of("quantity", 2))
 *     .build();
 * }</pre>
 *
 * @param eventId       globally unique event identifier
 * @param eventType     discriminator string; expected value is {@code "cart.item.added"}
 * @param timestamp     UTC instant the cart action occurred
 * @param userId        user or anonymous session identifier
 * @param sessionId     browser/app session identifier
 * @param correlationId trace correlation across upstream events
 * @param productId     identifier of the product added to the cart
 * @param metadata      arbitrary key-value pairs from the upstream event payload
 */
public record ProductAddedToCartEvent(
        String eventId,
        String eventType,
        Instant timestamp,
        String userId,
        String sessionId,
        String correlationId,
        String productId,
        Map<String, Serializable> metadata)
        implements ClickEvent {

    public ProductAddedToCartEvent {
        Objects.requireNonNull(eventId, "eventId must not be null");
        Objects.requireNonNull(eventType, "eventType must not be null");
        Objects.requireNonNull(timestamp, "timestamp must not be null");
        Objects.requireNonNull(userId, "userId must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(productId, "productId must not be null");
    }

    @Override
    public String toString() {
        return "ProductAddedToCartEvent[eventId=" + eventId + ", eventType=" + eventType + ", timestamp=" + timestamp
                + ", productId=" + productId + "]";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String eventId;
        private String eventType;
        private Instant timestamp;
        private String userId;
        private String sessionId;
        private String correlationId;
        private String productId;
        private Map<String, Serializable> metadata;

        private Builder() {}

        public Builder eventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder correlationId(String correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder metadata(Map<String, Serializable> metadata) {
            this.metadata = metadata;
            return this;
        }

        public ProductAddedToCartEvent build() {
            return new ProductAddedToCartEvent(
                    eventId, eventType, timestamp, userId, sessionId, correlationId, productId, metadata);
        }
    }
}
