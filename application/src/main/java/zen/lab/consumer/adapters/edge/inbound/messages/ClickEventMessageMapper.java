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

@Component
public class ClickEventMessageMapper {

    public ClickEvent toDomain(ClickEventMessage message) {
        return switch (message) {
            case ProductViewedEventMessage m -> toProductViewedEvent(m);
            case ProductAddedToCartEventMessage m -> toProductAddedToCartEvent(m);
        };
    }

    private ProductViewedEvent toProductViewedEvent(ProductViewedEventMessage message) {
        return ProductViewedEvent.builder()
                .eventId(message.getEventId())
                .eventType(message.getEventType())
                .timestamp(message.getTimestamp())
                .userId(message.getUserId())
                .sessionId(message.getSessionId())
                .correlationId(message.getCorrelationId())
                .product(toProduct(message.getProduct()))
                .context(toContext(message.getContext()))
                .build();
    }

    private ProductAddedToCartEvent toProductAddedToCartEvent(ProductAddedToCartEventMessage message) {
        return ProductAddedToCartEvent.builder()
                .eventId(message.getEventId())
                .eventType(message.getEventType())
                .timestamp(message.getTimestamp())
                .userId(message.getUserId())
                .sessionId(message.getSessionId())
                .correlationId(message.getCorrelationId())
                .productId(message.getProductId())
                .metadata(message.getMetadata())
                .build();
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
                msg.getImageUrl()
        );
    }

    private Context toContext(ContextMessage msg) {
        if (msg == null) return null;
        return new Context(
                msg.getPageId(),
                msg.getPosition(),
                msg.getSource(),
                msg.getReferrer(),
                msg.getSearchQuery()
        );
    }
}
