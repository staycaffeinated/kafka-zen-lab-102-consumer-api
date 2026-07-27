package zen.lab.consumer.domain.events;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProductAddedToCartEventTest {

    @Test
    void toStringMustNotRevealPersonallyIdentifiableInformation() {
        var event = ProductAddedToCartEvent.builder()
                .productId("product-123")
                .sessionId("session-123")
                .correlationId("correlation-123")
                .userId("user-123")
                .metadata(Map.of())
                .build();

        assertThat(event.toString())
                .doesNotContain("user-123")
                .doesNotContain("session-123")
                .doesNotContain("correlation-123");
    }

}
