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
        String imageUrl) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
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

        private Builder() {}

        public Builder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder subCategory(String subCategory) {
            this.subCategory = subCategory;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder priceCurrency(String priceCurrency) {
            this.priceCurrency = priceCurrency;
            return this;
        }

        public Builder inStock(boolean inStock) {
            this.inStock = inStock;
            return this;
        }

        public Builder inventoryCount(Long inventoryCount) {
            this.inventoryCount = inventoryCount;
            return this;
        }

        public Builder ratingAverage(BigDecimal ratingAverage) {
            this.ratingAverage = ratingAverage;
            return this;
        }

        public Builder ratingCount(BigInteger ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public Builder tags(Set<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Product build() {
            return new Product(
                    productId,
                    sku,
                    name,
                    slug,
                    category,
                    subCategory,
                    brand,
                    price,
                    priceCurrency,
                    inStock,
                    inventoryCount,
                    ratingAverage,
                    ratingCount,
                    tags,
                    imageUrl);
        }
    }
}
