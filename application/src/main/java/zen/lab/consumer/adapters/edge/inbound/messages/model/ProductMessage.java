package zen.lab.consumer.adapters.edge.inbound.messages.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
