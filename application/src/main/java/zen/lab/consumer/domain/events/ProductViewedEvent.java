package zen.lab.consumer.domain.events;

import java.time.Instant;
import java.util.Objects;
import org.jspecify.annotations.NonNull;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

/**
 * Domain event that records a user viewing a product detail page.
 *
 * <p>This record is an immutable value object in the domain layer. It carries a full
 * snapshot of the {@link Product} as it appeared at the moment of the view, together
 * with the page {@link Context} describing where the product was displayed.
 *
 * <p>Use {@link #builder()} to construct instances:
 * <pre>{@code
 * ProductViewedEvent event = ProductViewedEvent.builder()
 *     .eventId("evt-001")
 *     .eventType("product.viewed")
 *     .timestamp(Instant.now())
 *     .userId("usr-42")
 *     .sessionId("sess-xyz")
 *     .correlationId("corr-abc")
 *     .product(product)
 *     .context(context)
 *     .build();
 * }</pre>
 *
 * @param eventId       globally unique event identifier
 * @param eventType     discriminator string; expected value is {@code "product.viewed"}
 * @param timestamp     UTC instant the view occurred
 * @param userId        user or anonymous session identifier
 * @param sessionId     browser/app session identifier
 * @param correlationId trace correlation across upstream events
 * @param product       full product snapshot at view time
 * @param context       page context at view time
 */
public record ProductViewedEvent(
        String eventId,
        String eventType,
        Instant timestamp,
        String userId,
        String sessionId,
        String correlationId,
        Product product,
        Context context)
        implements ClickEvent {

    public ProductViewedEvent {
        Objects.requireNonNull(eventId, "eventId must not be null");
        Objects.requireNonNull(eventType, "eventType must not be null");
        Objects.requireNonNull(timestamp, "timestamp must not be null");
        Objects.requireNonNull(userId, "userId must not be null");
        Objects.requireNonNull(sessionId, "sessionId must not be null");
        Objects.requireNonNull(product, "product must not be null");
        Objects.requireNonNull(context, "context must not be null");
    }

    @Override
    @NonNull
    public String toString() {
        return "ProductViewedEvent[eventId=" + eventId + ", eventType=" + eventType + ", timestamp=" + timestamp
                + ", product=" + product + ", context=" + context + "]";
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
        private Product product;
        private Context context;

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

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public ProductViewedEvent build() {
            return new ProductViewedEvent(
                    eventId, eventType, timestamp, userId, sessionId, correlationId, product, context);
        }
    }
}
