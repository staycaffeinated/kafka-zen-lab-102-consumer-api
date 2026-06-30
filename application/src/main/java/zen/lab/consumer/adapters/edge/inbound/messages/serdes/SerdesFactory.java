package zen.lab.consumer.adapters.edge.inbound.messages.serdes;

import jakarta.annotation.Nonnull;
import java.util.Objects;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;
import zen.lab.consumer.domain.events.ClickEvent;
import zen.lab.consumer.domain.events.ProductAddedToCartEvent;
import zen.lab.consumer.domain.events.ProductViewedEvent;

/**
 * This returns `Serde` instances, which have both the serializer and deserializer
 * for a given event.
 * <p></p>
 * I've seen several repositories that follow this pattern, so I've included it here.
 * The idea is that, while defining your topology, you can fetch the serdes you need
 * by calling, for example, `SerdesFactory.PurchaseEvent()` or `SerdesFactory.PaymentEvent()`.
 *
 */
@Component
@SuppressWarnings({
    "java:S125", // allow code examples in comment blocks
    "java:S1068", // the unused objectMapper will get used once Serdes instances are added
    "java:S100" // allow capitalized method names
})
public class SerdesFactory {

    private final JsonMapper jsonMapper;

    public SerdesFactory(@Nonnull JsonMapper jsonMapper) {
        this.jsonMapper = Objects.requireNonNull(jsonMapper, "The JsonMapper must not be null");
    }

    public Serde<ClickEvent> clickEvent() {
        JacksonSerializer<ClickEvent> jsonSerializer = new JacksonSerializer<>(jsonMapper);
        JacksonDeserializer<ClickEvent> jsonDeserialize = new JacksonDeserializer<>(ClickEvent.class, jsonMapper);

        return Serdes.serdeFrom(jsonSerializer, jsonDeserialize);
    }

    public Serde<ProductAddedToCartEvent> productAddedToCartEvent() {
        JacksonSerializer<ProductAddedToCartEvent> jsonSerializer = new JacksonSerializer<>(jsonMapper);
        JacksonDeserializer<ProductAddedToCartEvent> jsonDeserialize =
                new JacksonDeserializer<>(ProductAddedToCartEvent.class, jsonMapper);

        return Serdes.serdeFrom(jsonSerializer, jsonDeserialize);
    }

    public Serde<ProductViewedEvent> productViewedEvent() {
        JacksonSerializer<ProductViewedEvent> jsonSerializer = new JacksonSerializer<>(jsonMapper);
        JacksonDeserializer<ProductViewedEvent> jsonDeserialize =
                new JacksonDeserializer<>(ProductViewedEvent.class, jsonMapper);

        return Serdes.serdeFrom(jsonSerializer, jsonDeserialize);
    }
}
