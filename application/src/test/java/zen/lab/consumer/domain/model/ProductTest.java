package zen.lab.consumer.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void builderMustConstructProductWithAllFields() {
        var product = Product.builder()
                .productId("product-123")
                .sku("SKU-456")
                .name("Running Shoes")
                .slug("running-shoes")
                .category("Footwear")
                .subCategory("Running")
                .brand("SpeedCo")
                .price(new BigDecimal("99.99"))
                .priceCurrency("USD")
                .inStock(true)
                .inventoryCount(42L)
                .ratingAverage(new BigDecimal("4.5"))
                .ratingCount(BigInteger.valueOf(200))
                .tags(Set.of("sport", "running"))
                .imageUrl("https://example.com/image.jpg")
                .build();

        assertThat(product.productId()).isEqualTo("product-123");
        assertThat(product.sku()).isEqualTo("SKU-456");
        assertThat(product.name()).isEqualTo("Running Shoes");
        assertThat(product.slug()).isEqualTo("running-shoes");
        assertThat(product.category()).isEqualTo("Footwear");
        assertThat(product.subCategory()).isEqualTo("Running");
        assertThat(product.brand()).isEqualTo("SpeedCo");
        assertThat(product.price()).isEqualByComparingTo("99.99");
        assertThat(product.priceCurrency()).isEqualTo("USD");
        assertThat(product.inStock()).isTrue();
        assertThat(product.inventoryCount()).isEqualTo(42L);
        assertThat(product.ratingAverage()).isEqualByComparingTo("4.5");
        assertThat(product.ratingCount()).isEqualTo(BigInteger.valueOf(200));
        assertThat(product.tags()).containsExactlyInAnyOrder("sport", "running");
        assertThat(product.imageUrl()).isEqualTo("https://example.com/image.jpg");
    }

    @Test
    void builderMustConstructProductWithNullOptionalFields() {
        var product = Product.builder()
                .productId("product-123")
                .name("Running Shoes")
                .inStock(false)
                .build();

        assertThat(product.productId()).isEqualTo("product-123");
        assertThat(product.name()).isEqualTo("Running Shoes");
        assertThat(product.inStock()).isFalse();
        assertThat(product.sku()).isNull();
        assertThat(product.slug()).isNull();
        assertThat(product.category()).isNull();
        assertThat(product.subCategory()).isNull();
        assertThat(product.brand()).isNull();
        assertThat(product.price()).isNull();
        assertThat(product.priceCurrency()).isNull();
        assertThat(product.inventoryCount()).isNull();
        assertThat(product.ratingAverage()).isNull();
        assertThat(product.ratingCount()).isNull();
        assertThat(product.tags()).isNull();
        assertThat(product.imageUrl()).isNull();
    }
}
