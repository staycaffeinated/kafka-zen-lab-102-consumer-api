package zen.lab.consumer.adapters.edge.inbound.messages.model;

import java.io.Serializable;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Inbound Kafka message DTO for the {@code "cart.item.added"} event type.
 *
 * <p>Carries the identifier of the product added to the cart and an open-ended
 * {@code metadata} map for upstream-defined key-value pairs (e.g., quantity).
 * The metadata map preserves {@link java.io.Serializable} values as received.
 *
 * <p>Mapped to {@link zen.lab.consumer.domain.events.ProductAddedToCartEvent} by
 * {@code ClickEventMessageMapper}.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public final class ProductAddedToCartEventMessage extends ClickEventMessage {

    private String productId;
    private Map<String, Serializable> metadata;
}
