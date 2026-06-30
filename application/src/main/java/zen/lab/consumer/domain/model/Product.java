package zen.lab.consumer.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuppressWarnings({"java:S107 " // yes, builder has more than 7 methods.
})
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
    private boolean inStock;
    private Long inventoryCount;
    private BigDecimal ratingAverage;
    private BigInteger ratingCount;
    private Set<String> tags;
    private String imageUrl;
}
