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
}
