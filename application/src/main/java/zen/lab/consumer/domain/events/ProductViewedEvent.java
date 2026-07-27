package zen.lab.consumer.domain.events;

import java.time.Instant;
import org.jspecify.annotations.NonNull;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

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
