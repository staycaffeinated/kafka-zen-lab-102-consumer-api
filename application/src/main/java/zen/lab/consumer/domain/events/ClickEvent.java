package zen.lab.consumer.domain.events;

import java.time.Instant;

public sealed interface ClickEvent permits ProductViewedEvent, ProductAddedToCartEvent {
    String eventId();

    String eventType();

    Instant timestamp();

    String userId();

    String sessionId();

    String correlationId();
}
