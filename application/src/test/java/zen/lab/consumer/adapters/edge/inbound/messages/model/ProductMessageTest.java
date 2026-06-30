package zen.lab.consumer.adapters.edge.inbound.messages.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

class ProductMessageTest {

    private static final String PRODUCT_ID = "prod-001";
    private static final String SKU = "SKU-XYZ";
    private static final String NAME = "Blue Sneaker";
    private static final String SLUG = "blue-sneaker";
    private static final String CATEGORY = "Footwear";
    private static final String SUB_CATEGORY = "Sneakers";
    private static final String BRAND = "Acme";
    private static final BigDecimal PRICE = new BigDecimal("49.99");
    private static final String PRICE_CURRENCY = "USD";
    private static final boolean IN_STOCK = true;
    private static final Long INVENTORY_COUNT = 120L;
    private static final BigDecimal RATING_AVERAGE = new BigDecimal("4.5");
    private static final BigInteger RATING_COUNT = BigInteger.valueOf(300);
    private static final Set<String> TAGS = Set.of("sale", "new-arrival");
    private static final String IMAGE_URL = "https://example.com/img/blue-sneaker.jpg";

    private static ProductMessage fullProduct() {
        return ProductMessage.builder()
                .productId(PRODUCT_ID)
                .sku(SKU)
                .name(NAME)
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

    @Nested
    class Construction {

        @Test
        void builderSetsAllFields() {
            ProductMessage msg = fullProduct();

            assertThat(msg.getProductId()).isEqualTo(PRODUCT_ID);
            assertThat(msg.getSku()).isEqualTo(SKU);
            assertThat(msg.getName()).isEqualTo(NAME);
            assertThat(msg.getSlug()).isEqualTo(SLUG);
            assertThat(msg.getCategory()).isEqualTo(CATEGORY);
            assertThat(msg.getSubCategory()).isEqualTo(SUB_CATEGORY);
            assertThat(msg.getBrand()).isEqualTo(BRAND);
            assertThat(msg.getPrice()).isEqualByComparingTo(PRICE);
            assertThat(msg.getPriceCurrency()).isEqualTo(PRICE_CURRENCY);
            assertThat(msg.isInStock()).isTrue();
            assertThat(msg.getInventoryCount()).isEqualTo(INVENTORY_COUNT);
            assertThat(msg.getRatingAverage()).isEqualByComparingTo(RATING_AVERAGE);
            assertThat(msg.getRatingCount()).isEqualTo(RATING_COUNT);
            assertThat(msg.getTags()).containsExactlyInAnyOrderElementsOf(TAGS);
            assertThat(msg.getImageUrl()).isEqualTo(IMAGE_URL);
        }

        @Test
        void allArgsConstructorSetsAllFields() {
            ProductMessage msg = new ProductMessage(
                    PRODUCT_ID,
                    SKU,
                    NAME,
                    SLUG,
                    CATEGORY,
                    SUB_CATEGORY,
                    BRAND,
                    PRICE,
                    PRICE_CURRENCY,
                    IN_STOCK,
                    INVENTORY_COUNT,
                    RATING_AVERAGE,
                    RATING_COUNT,
                    TAGS,
                    IMAGE_URL);

            assertThat(msg.getProductId()).isEqualTo(PRODUCT_ID);
            assertThat(msg.getSku()).isEqualTo(SKU);
            assertThat(msg.getName()).isEqualTo(NAME);
            assertThat(msg.getSlug()).isEqualTo(SLUG);
            assertThat(msg.getCategory()).isEqualTo(CATEGORY);
            assertThat(msg.getSubCategory()).isEqualTo(SUB_CATEGORY);
            assertThat(msg.getBrand()).isEqualTo(BRAND);
            assertThat(msg.getPrice()).isEqualByComparingTo(PRICE);
            assertThat(msg.getPriceCurrency()).isEqualTo(PRICE_CURRENCY);
            assertThat(msg.isInStock()).isTrue();
            assertThat(msg.getInventoryCount()).isEqualTo(INVENTORY_COUNT);
            assertThat(msg.getRatingAverage()).isEqualByComparingTo(RATING_AVERAGE);
            assertThat(msg.getRatingCount()).isEqualTo(RATING_COUNT);
            assertThat(msg.getTags()).containsExactlyInAnyOrderElementsOf(TAGS);
            assertThat(msg.getImageUrl()).isEqualTo(IMAGE_URL);
        }

        @Test
        void noArgsConstructorProducesNullFields() {
            ProductMessage msg = new ProductMessage();

            assertThat(msg.getProductId()).isNull();
            assertThat(msg.getSku()).isNull();
            assertThat(msg.getName()).isNull();
            assertThat(msg.getPrice()).isNull();
            assertThat(msg.isInStock()).isFalse();
            assertThat(msg.getInventoryCount()).isNull();
        }

        @Test
        void builderInStockDefaultsFalse() {
            ProductMessage msg = ProductMessage.builder().productId(PRODUCT_ID).build();

            assertThat(msg.isInStock()).isFalse();
        }
    }

    @Nested
    class EqualsAndHashCode {

        @Test
        void twoIdenticalBuildsAreEqual() {
            ProductMessage a = fullProduct();
            ProductMessage b = fullProduct();

            assertThat(a).isEqualTo(b).hasSameHashCodeAs(b);
        }

        @Test
        void differentProductIdNotEqual() {
            ProductMessage a = fullProduct();
            ProductMessage b =
                    ProductMessage.builder().productId("other-id").sku(SKU).build();

            assertThat(a).isNotEqualTo(b);
        }

        @Test
        void notEqualToNull() {
            assertThat(fullProduct()).isNotEqualTo(null);
        }

        @Test
        void equalToSelf() {
            ProductMessage msg = fullProduct();
            assertThat(msg).isEqualTo(msg);
        }
    }

    @Nested
    class ToStringMethod {

        @Test
        void containsProductId() {
            assertThat(fullProduct().toString()).contains(PRODUCT_ID);
        }

        @Test
        void containsName() {
            assertThat(fullProduct().toString()).contains(NAME);
        }
    }

    @Nested
    class JsonDeserialization {

        private final JsonMapper jsonMapper = JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                .build();

        @Test
        void deserializesAllFieldsFromJson() throws Exception {
            String json =
                    """
                    {
                      "productId": "%s",
                      "sku": "%s",
                      "name": "%s",
                      "slug": "%s",
                      "category": "%s",
                      "subCategory": "%s",
                      "brand": "%s",
                      "price": 49.99,
                      "priceCurrency": "%s",
                      "inStock": true,
                      "inventoryCount": 120,
                      "ratingAverage": 4.5,
                      "ratingCount": 300,
                      "tags": ["sale", "new-arrival"],
                      "imageUrl": "%s"
                    }
                    """
                            .formatted(
                                    PRODUCT_ID,
                                    SKU,
                                    NAME,
                                    SLUG,
                                    CATEGORY,
                                    SUB_CATEGORY,
                                    BRAND,
                                    PRICE_CURRENCY,
                                    IMAGE_URL);

            ProductMessage msg = jsonMapper.readValue(json, ProductMessage.class);

            assertThat(msg.getProductId()).isEqualTo(PRODUCT_ID);
            assertThat(msg.getSku()).isEqualTo(SKU);
            assertThat(msg.getName()).isEqualTo(NAME);
            assertThat(msg.getPrice()).isEqualByComparingTo(PRICE);
            assertThat(msg.isInStock()).isTrue();
            assertThat(msg.getInventoryCount()).isEqualTo(INVENTORY_COUNT);
            assertThat(msg.getRatingAverage()).isEqualByComparingTo(RATING_AVERAGE);
            assertThat(msg.getRatingCount()).isEqualTo(RATING_COUNT);
            assertThat(msg.getTags()).containsExactlyInAnyOrder("sale", "new-arrival");
            assertThat(msg.getImageUrl()).isEqualTo(IMAGE_URL);
        }

        @Test
        void deserializesInStockFalseWhenAbsent() throws Exception {
            ProductMessage msg = jsonMapper.readValue("{\"productId\":\"p1\"}", ProductMessage.class);

            assertThat(msg.isInStock()).isFalse();
        }

        @Test
        void deserializesPartialJsonWithNullsForMissingFields() throws Exception {
            String json =
                    """
                    {
                      "productId": "%s",
                      "sku": "%s"
                    }
                    """
                            .formatted(PRODUCT_ID, SKU);

            ProductMessage msg = jsonMapper.readValue(json, ProductMessage.class);

            assertThat(msg.getProductId()).isEqualTo(PRODUCT_ID);
            assertThat(msg.getSku()).isEqualTo(SKU);
            assertThat(msg.getName()).isNull();
            assertThat(msg.getPrice()).isNull();
            assertThat(msg.getTags()).isNull();
        }

        @Test
        void deserializesEmptyJsonObjectToAllNullFields() throws Exception {
            ProductMessage msg = jsonMapper.readValue("{}", ProductMessage.class);

            assertThat(msg.getProductId()).isNull();
            assertThat(msg.getPrice()).isNull();
            assertThat(msg.isInStock()).isFalse();
        }
    }
}
