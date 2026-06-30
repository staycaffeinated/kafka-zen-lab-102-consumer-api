package zen.lab.consumer.domain.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import zen.lab.consumer.domain.model.Context;
import zen.lab.consumer.domain.model.Product;

/**
 * Builds an event that, in JSON, looks like this:
 * <code>
 * {
 * "event_type": "product.viewed",
 * "event_id": "evt_7f3a9b12",
 * "timestamp": "2026-06-12T14:23:01Z",
 * "user_id": "u_8821",
 * "session_id": "s_abc123",
 * "product": {
 * "id": "p_42",
 * "sku": "SHOE-NK-AIR-10-BLK",
 * "name": "Nike Air Max 10",
 * "slug": "nike-air-max-10-black",
 * "category": "footwear",
 * "subcategory": "running",
 * "brand": "Nike",
 * "price_cents": 12999,
 * "currency": "USD",
 * "in_stock": true,
 * "inventory_count": 34,
 * "rating_average": 4.3,
 * "rating_count": 812,
 * "tags": ["running", "sale", "new-arrival"],
 * "image_url": "https://cdn.example.com/products/p_42/main.jpg"
 * },
 * "context": {
 * "page": "search_results",
 * "position": 3,
 * "source": "recommendation_engine",
 * "referrer": "homepage_banner",
 * "search_query": "black running shoes"
 * }
 * }
 * </code>
 */
@Getter
@SuperBuilder
@RequiredArgsConstructor
@ToString(callSuper = true)
public final class ProductViewedEvent extends ClickEvent {
    private Product product;
    private Context context;
}
