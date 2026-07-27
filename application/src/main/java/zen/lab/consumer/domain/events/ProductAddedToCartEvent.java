package zen.lab.consumer.domain.events;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

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

        public Builder eventId(String eventId) { this.eventId = eventId; return this; }
        public Builder eventType(String eventType) { this.eventType = eventType; return this; }
        public Builder timestamp(Instant timestamp) { this.timestamp = timestamp; return this; }
        public Builder userId(String userId) { this.userId = userId; return this; }
        public Builder sessionId(String sessionId) { this.sessionId = sessionId; return this; }
        public Builder correlationId(String correlationId) { this.correlationId = correlationId; return this; }
        public Builder productId(String productId) { this.productId = productId; return this; }
        public Builder metadata(Map<String, Serializable> metadata) { this.metadata = metadata; return this; }

        public ProductAddedToCartEvent build() {
            return new ProductAddedToCartEvent(eventId, eventType, timestamp, userId, sessionId, correlationId, productId, metadata);
        }
    }

}
