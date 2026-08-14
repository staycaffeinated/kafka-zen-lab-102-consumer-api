package zen.lab.consumer.adapters.port.inbound.messages.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Inbound Kafka message DTO that carries the product snapshot embedded in a
 * {@link ProductViewedEventMessage}.
 *
 * <p>Fields mirror those of the domain {@link zen.lab.consumer.domain.model.Product} record and
 * are mapped 1-to-1 by {@code ClickEventMessageMapper.toProduct()}.
 *
 * <p>Jackson populates instances via field visibility ({@code @JsonAutoDetect}) so no
 * explicit {@code @JsonProperty} annotations are required. The Sonar rule S107 (constructor has
 * too many parameters) is suppressed because the field count is intrinsic to the domain model.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SuppressWarnings({"java:S107" // yes, builder has 7+ methods; class has 15 instance variables
})
public class ProductMessage {

    private String productId;
    private String sku;
    private String name;
    private String slug;
    private String category;
    private String subCategory;
    private String brand;
    private BigDecimal price;
    private String priceCurrency;
    private boolean inStock;
    private Long inventoryCount;
    private BigDecimal ratingAverage;
    private BigInteger ratingCount;
    private Set<String> tags;
    private String imageUrl;

    public ProductMessage(
            String productId,
            String sku,
            String name,
            String slug,
            String category,
            String subCategory,
            String brand,
            BigDecimal price,
            String priceCurrency,
            boolean inStock,
            Long inventoryCount,
            BigDecimal ratingAverage,
            BigInteger ratingCount,
            Set<String> tags,
            String imageUrl) {
        this.productId = productId;
        this.sku = sku;
        this.name = name;
        this.slug = slug;
        this.category = category;
        this.subCategory = subCategory;
        this.brand = brand;
        this.price = price;
        this.priceCurrency = priceCurrency;
        this.inStock = inStock;
        this.inventoryCount = inventoryCount;
        this.ratingAverage = ratingAverage;
        this.ratingCount = ratingCount;
        this.tags = tags;
        this.imageUrl = imageUrl;
    }
}
