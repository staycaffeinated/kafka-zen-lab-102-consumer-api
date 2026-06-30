package zen.lab.consumer.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Product {

    private String productId;
    private String sku;
    private String name;
    private String slug;
    private String category;
    private String subCategory;
    private String brand;
    private BigDecimal price;
    private String priceCurrency;
    private boolean isInStock;
    private Long inventoryCount;
    private BigDecimal ratingAverage;
    private BigInteger ratingCount;
    private Set<String> tags;
    private String imageUrl;
}
