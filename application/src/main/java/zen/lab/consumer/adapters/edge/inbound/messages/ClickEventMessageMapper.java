package zen.lab.consumer.adapters.edge.inbound.messages;

import org.springframework.stereotype.Component;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ClickEventMessage;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ContextMessage;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ProductAddedToCartEventMessage;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ProductMessage;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ProductViewedEventMessage;
import zen.lab.consumer.domain.events.ClickEvent;
import zen.lab.consumer.domain.events.ProductAddedToCartEvent;
import zen.lab.consumer.domain.events.ProductViewedEvent;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

/**
 * Maps inbound Kafka message objects to domain events.
 *
 * <p>This component sits between the Kafka adapter and the domain layer, translating
 * the mutable, Jackson-annotated {@link ClickEventMessage} hierarchy into immutable
 * domain records from the {@link zen.lab.consumer.domain.events} package.
 *
 * <p>The mapping is performed via a sealed-interface pattern match. Because
 * {@code ClickEventMessage} is a sealed class and {@code ClickEvent} is a sealed
 * interface, the compiler enforces exhaustiveness: adding a new message subtype
 * without updating the {@code switch} expression causes a compile error.
 */
@Component
public class ClickEventMessageMapper {

    /**
     * Maps the given {@link ClickEventMessage} to a domain {@link ClickEvent}.
     *
     * <p>Uses an exhaustive pattern-matching {@code switch} over the sealed
     * {@code ClickEventMessage} hierarchy. The switch has no default branch;
     * any unrecognised subtype will result in a compile error.
     *
     * @param message the inbound Kafka message; must not be null
     * @return the corresponding domain event; never null
     */
    public ClickEvent toDomain(ClickEventMessage message) {
        return switch (message) {
            case ProductViewedEventMessage m -> toProductViewedEvent(m);
            case ProductAddedToCartEventMessage m -> toProductAddedToCartEvent(m);
        };
    }

    private ProductViewedEvent toProductViewedEvent(ProductViewedEventMessage message) {
        return new ProductViewedEvent(
                message.getEventId(),
                message.getEventType(),
                message.getTimestamp(),
                message.getUserId(),
                message.getSessionId(),
                message.getCorrelationId(),
                toProduct(message.getProduct()),
                toContext(message.getContext()));
    }

    private ProductAddedToCartEvent toProductAddedToCartEvent(ProductAddedToCartEventMessage message) {
        return new ProductAddedToCartEvent(
                message.getEventId(),
                message.getEventType(),
                message.getTimestamp(),
                message.getUserId(),
                message.getSessionId(),
                message.getCorrelationId(),
                message.getProductId(),
                message.getMetadata());
    }

    private Product toProduct(ProductMessage msg) {
        if (msg == null) return null;
        return new Product(
                msg.getProductId(),
                msg.getSku(),
                msg.getName(),
                msg.getSlug(),
                msg.getCategory(),
                msg.getSubCategory(),
                msg.getBrand(),
                msg.getPrice(),
                msg.getPriceCurrency(),
                msg.isInStock(),
                msg.getInventoryCount(),
                msg.getRatingAverage(),
                msg.getRatingCount(),
                msg.getTags(),
                msg.getImageUrl());
    }

    private Context toContext(ContextMessage msg) {
        if (msg == null) return null;
        return new Context(
                msg.getPageId(), msg.getPosition(), msg.getSource(), msg.getReferrer(), msg.getSearchQuery());
    }
}
