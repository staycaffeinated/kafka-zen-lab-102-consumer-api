package zen.lab.consumer.domain.events;

import java.time.Instant;

/**
 * Root of the click-stream domain event hierarchy.
 *
 * <p>This is a sealed interface: the only permitted implementations are
 * {@link ProductViewedEvent} and {@link ProductAddedToCartEvent}. The restriction
 * allows the compiler to enforce exhaustive pattern matching in switch expressions
 * (e.g., in {@code ClickEventMessageMapper.toDomain()}), eliminating the need for a
 * default branch and making unhandled event types a compile-time error.
 *
 * <p>All implementations are Java records, ensuring immutability by construction.
 * The {@code eventType} value matches the JSON discriminator field used by the
 * upstream Kafka message model.
 */
public sealed interface ClickEvent permits ProductViewedEvent, ProductAddedToCartEvent {

    /**
     * Returns the globally unique identifier for this event instance.
     *
     * @return a non-null, non-empty event ID string
     */
    String eventId();

    /**
     * Returns the event type discriminator string.
     *
     * <p>Known values:
     * <ul>
     *   <li>{@code "product.viewed"} — corresponds to {@link ProductViewedEvent}</li>
     *   <li>{@code "cart.item.added"} — corresponds to {@link ProductAddedToCartEvent}</li>
     * </ul>
     *
     * @return the event type string, never null
     */
    String eventType();

    /**
     * Returns the UTC instant at which this event occurred on the client side.
     *
     * @return the event timestamp, never null
     */
    Instant timestamp();

    /**
     * Returns the identifier of the user who triggered this event.
     * May be an anonymous session-scoped ID or an authenticated user ID.
     *
     * @return the user identifier, never null
     */
    String userId();

    /**
     * Returns the browser or app session identifier in which this event occurred.
     *
     * @return the session identifier, never null
     */
    String sessionId();

    /**
     * Returns the correlation identifier that ties this event to other events
     * in the same upstream trace.
     *
     * @return the correlation identifier, never null
     */
    String correlationId();
}
