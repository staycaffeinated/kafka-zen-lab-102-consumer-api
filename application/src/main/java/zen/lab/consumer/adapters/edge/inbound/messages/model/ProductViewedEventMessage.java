package zen.lab.consumer.adapters.edge.inbound.messages.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Inbound Kafka message DTO for the {@code "product.viewed"} event type.
 *
 * <p>Carries a {@link ProductMessage} snapshot of the viewed product and a
 * {@link ContextMessage} describing the page context at the time of the view.
 * Both fields may be {@code null} if the upstream producer omits them; the mapper
 * handles null guards before constructing domain objects.
 *
 * <p>Mapped to {@link zen.lab.consumer.domain.events.ProductViewedEvent} by
 * {@code ClickEventMessageMapper}.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public final class ProductViewedEventMessage extends ClickEventMessage {

    private ProductMessage product;
    private ContextMessage context;
}
