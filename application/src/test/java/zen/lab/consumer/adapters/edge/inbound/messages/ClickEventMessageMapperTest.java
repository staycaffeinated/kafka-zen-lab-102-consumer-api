package zen.lab.consumer.adapters.edge.inbound.messages;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ContextMessage;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ProductMessage;
import zen.lab.consumer.adapters.edge.inbound.messages.model.ProductViewedEventMessage;
import zen.lab.consumer.domain.events.ProductViewedEvent;

class ClickEventMessageMapperTest {

    private final ClickEventMessageMapper mapper = new ClickEventMessageMapper();

    private static final String EVENT_ID = "evt-001";
    private static final String EVENT_TYPE = "product.viewed";
    private static final Instant TIMESTAMP = Instant.parse("2024-01-15T10:00:00Z");
    private static final String USER_ID = "user-abc";
    private static final String SESSION_ID = "session-xyz";
    private static final String CORRELATION_ID = "corr-123";
    private static final String PARTITION_KEY = "partition-1";

    private static final String PRODUCT_ID = "prod-001";
    private static final String SKU = "SKU-123";
    private static final String PRODUCT_NAME = "Blue Sneakers";
    private static final String SLUG = "blue-sneakers";
    private static final String CATEGORY = "footwear";
    private static final String SUB_CATEGORY = "sneakers";
    private static final String BRAND = "Nike";
    private static final BigDecimal PRICE = new BigDecimal("89.99");
    private static final String PRICE_CURRENCY = "USD";
    private static final boolean IN_STOCK = true;
    private static final Long INVENTORY_COUNT = 42L;
    private static final BigDecimal RATING_AVERAGE = new BigDecimal("4.5");
    private static final BigInteger RATING_COUNT = BigInteger.valueOf(120);
    private static final Set<String> TAGS = Set.of("sale", "trending");
    private static final String IMAGE_URL = "https://example.com/img.jpg";

    private static final String PAGE_ID = "page-home";
    private static final Long POSITION = 2L;
    private static final String SOURCE = "search";
    private static final String REFERRER = "https://example.com";
    private static final String SEARCH_QUERY = "blue sneakers";

    @Nested
    class NullHandling {

        @Test
        void nullProductMessageMapsToNullDomainProduct() {
            ProductViewedEventMessage message = ProductViewedEventMessage.builder()
                    .eventId(EVENT_ID)
                    .eventType(EVENT_TYPE)
                    .product(null)
                    .context(aContextMessage())
                    .build();

            ProductViewedEvent result = (ProductViewedEvent) mapper.toDomain(message);

            assertThat(result.getProduct()).isNull();
        }

        @Test
        void nullContextMessageMapsToNullDomainContext() {
            ProductViewedEventMessage message = ProductViewedEventMessage.builder()
                    .eventId(EVENT_ID)
                    .eventType(EVENT_TYPE)
                    .product(aProductMessage())
                    .context(null)
                    .build();

            ProductViewedEvent result = (ProductViewedEvent) mapper.toDomain(message);

            assertThat(result.getContext()).isNull();
        }
    }

    @Nested
    class ProductViewedMapping {

        @Test
        void mapsAllBaseFieldsFromMessage() {
            ProductViewedEventMessage message = ProductViewedEventMessage.builder()
                    .eventId(EVENT_ID)
                    .eventType(EVENT_TYPE)
                    .timestamp(TIMESTAMP)
                    .userId(USER_ID)
                    .sessionId(SESSION_ID)
                    .correlationId(CORRELATION_ID)
                    .partitionKey(PARTITION_KEY)
                    .product(null)
                    .context(null)
                    .build();

            ProductViewedEvent result = (ProductViewedEvent) mapper.toDomain(message);

            assertThat(result.getEventId()).isEqualTo(EVENT_ID);
            assertThat(result.getEventType()).isEqualTo(EVENT_TYPE);
            assertThat(result.getTimestamp()).isEqualTo(TIMESTAMP);
            assertThat(result.getUserId()).isEqualTo(USER_ID);
            assertThat(result.getSessionId()).isEqualTo(SESSION_ID);
            assertThat(result.getCorrelationId()).isEqualTo(CORRELATION_ID);
        }

        @Test
        void mapsAllProductFieldsFromMessage() {
            ProductViewedEventMessage message = ProductViewedEventMessage.builder()
                    .eventId(EVENT_ID)
                    .eventType(EVENT_TYPE)
                    .product(aProductMessage())
                    .context(null)
                    .build();

            ProductViewedEvent result = (ProductViewedEvent) mapper.toDomain(message);

            assertThat(result.getProduct()).isNotNull();
            assertThat(result.getProduct().productId()).isEqualTo(PRODUCT_ID);
            assertThat(result.getProduct().sku()).isEqualTo(SKU);
            assertThat(result.getProduct().name()).isEqualTo(PRODUCT_NAME);
            assertThat(result.getProduct().slug()).isEqualTo(SLUG);
            assertThat(result.getProduct().category()).isEqualTo(CATEGORY);
            assertThat(result.getProduct().subCategory()).isEqualTo(SUB_CATEGORY);
            assertThat(result.getProduct().brand()).isEqualTo(BRAND);
            assertThat(result.getProduct().price()).isEqualByComparingTo(PRICE);
            assertThat(result.getProduct().priceCurrency()).isEqualTo(PRICE_CURRENCY);
            assertThat(result.getProduct().inStock()).isEqualTo(IN_STOCK);
            assertThat(result.getProduct().inventoryCount()).isEqualTo(INVENTORY_COUNT);
            assertThat(result.getProduct().ratingAverage()).isEqualByComparingTo(RATING_AVERAGE);
            assertThat(result.getProduct().ratingCount()).isEqualTo(RATING_COUNT);
            assertThat(result.getProduct().tags()).containsExactlyInAnyOrderElementsOf(TAGS);
            assertThat(result.getProduct().imageUrl()).isEqualTo(IMAGE_URL);
        }

        @Test
        void mapsAllContextFieldsFromMessage() {
            ProductViewedEventMessage message = ProductViewedEventMessage.builder()
                    .eventId(EVENT_ID)
                    .eventType(EVENT_TYPE)
                    .product(null)
                    .context(aContextMessage())
                    .build();

            ProductViewedEvent result = (ProductViewedEvent) mapper.toDomain(message);

            assertThat(result.getContext()).isNotNull();
            assertThat(result.getContext().pageId()).isEqualTo(PAGE_ID);
            assertThat(result.getContext().position()).isEqualTo(POSITION);
            assertThat(result.getContext().source()).isEqualTo(SOURCE);
            assertThat(result.getContext().referrer()).isEqualTo(REFERRER);
            assertThat(result.getContext().searchQuery()).isEqualTo(SEARCH_QUERY);
        }
    }

    private static ProductMessage aProductMessage() {
        return ProductMessage.builder()
                .productId(PRODUCT_ID)
                .sku(SKU)
                .name(PRODUCT_NAME)
                .slug(SLUG)
                .category(CATEGORY)
                .subCategory(SUB_CATEGORY)
                .brand(BRAND)
                .price(PRICE)
                .priceCurrency(PRICE_CURRENCY)
                .inStock(IN_STOCK)
                .inventoryCount(INVENTORY_COUNT)
                .ratingAverage(RATING_AVERAGE)
                .ratingCount(RATING_COUNT)
                .tags(TAGS)
                .imageUrl(IMAGE_URL)
                .build();
    }

    private static ContextMessage aContextMessage() {
        return ContextMessage.builder()
                .pageId(PAGE_ID)
                .position(POSITION)
                .source(SOURCE)
                .referrer(REFERRER)
                .searchQuery(SEARCH_QUERY)
                .build();
    }
}
