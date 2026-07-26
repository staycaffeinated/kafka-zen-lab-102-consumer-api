package zen.lab.consumer.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

public record Product(
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
        String imageUrl) {}
