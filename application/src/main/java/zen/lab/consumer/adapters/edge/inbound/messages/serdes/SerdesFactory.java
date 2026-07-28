package zen.lab.consumer.adapters.edge.inbound.messages.serdes;

import jakarta.annotation.Nonnull;
import java.util.Objects;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

/**
 * Factory that produces {@code Serde} instances (serialiser + deserialiser pairs)
 * for use in Kafka Streams topology definitions.
 *
 * <p>Callers request a {@code Serde} by invoking a named factory method, for example:
 * <pre>{@code
 * Serde<ClickEventMessage> serde = serdesFactory.clickEventMessage();
 * }</pre>
 *
 * <p>Each method constructs a paired {@link JacksonSerializer} and
 * {@link JacksonDeserializer} backed by the shared {@link JsonMapper} bean.
 * No {@code Serde} methods are implemented yet; they are added here as new Kafka
 * Streams topologies are introduced.
 */
@Component
@SuppressWarnings({
    "java:S125", // allow code examples in comment blocks
    "java:S1068", // the unused objectMapper will get used once Serdes instances are added
    "java:S100" // allow capitalized method names
})
public class SerdesFactory {

    private final JsonMapper jsonMapper;

    /**
     * Creates a {@code SerdesFactory} backed by the given {@link JsonMapper}.
     *
     * @param jsonMapper the Jackson mapper used to build serialiser/deserialiser pairs;
     *                   must not be null
     * @throws NullPointerException if {@code jsonMapper} is null
     */
    public SerdesFactory(@Nonnull JsonMapper jsonMapper) {
        this.jsonMapper = Objects.requireNonNull(jsonMapper, "The JsonMapper must not be null");
    }
}
