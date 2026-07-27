package zen.lab.consumer.domain.events;

import java.time.Instant;
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
    public String toString() {
        return "ProductViewedEvent[eventId=" + eventId + ", eventType=" + eventType + ", timestamp=" + timestamp
                + ", product=" + product + ", context=" + context + "]";
    }
}
