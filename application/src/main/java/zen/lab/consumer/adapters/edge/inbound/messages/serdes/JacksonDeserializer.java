package zen.lab.consumer.adapters.port.inbound.messages.serdes;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Objects;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.json.JsonMapper;

/**
 * Generic Kafka {@link Deserializer} that converts raw JSON bytes to objects using a
 * provided {@link JsonMapper}.
 *
 * <p>A {@code null} byte array is treated as a {@code null} value (the Kafka convention
 * for tombstone records). Any deserialisation failure wraps the underlying exception in a
 * {@link org.apache.kafka.common.errors.SerializationException}.
 *
 * <p>The primary use-case is deserialising {@code ClickEventMessage} subtypes inside the
 * Kafka consumer container. Pass a {@code JsonMapper} that has the Jackson modules required
 * to handle the target type (e.g., {@code jackson-datatype-jsr310} for {@code Instant}
 * fields).
 *
 * @param <T> the type of object to deserialise
 */
public class JacksonDeserializer<T> implements Deserializer<T> {

    private final Class<T> destinationClass;

    private final JsonMapper jsonMapper;

    /**
     * Create a new JacksonDeserializer using a custom JsonMapper.
     * @param destinationClass the class of the object to deserialize to
     * @param jsonMapper the JsonMapper to use for deserialization
     */
    public JacksonDeserializer(@Nonnull Class<T> destinationClass, @Nonnull JsonMapper jsonMapper) {
        this.destinationClass = Objects.requireNonNull(destinationClass, "The destinationClass must not be null");
        this.jsonMapper = Objects.requireNonNull(jsonMapper, "The ObjectMapper must not be null");
    }

    /**
     * Create a new JacksonDeserializer with a default ObjectMapper.
     * @param destinationClass the class of the object to deserialize to
     */
    public JacksonDeserializer(@Nonnull Class<T> destinationClass) {
        this(destinationClass, new JsonMapper());
    }

    /**
     * Deserialises the given byte array into an instance of {@code T}.
     *
     * @param topic the Kafka topic name (used in error messages)
     * @param data  the raw JSON bytes; may be null
     * @return the deserialised object, or {@code null} if {@code data} is null
     * @throws org.apache.kafka.common.errors.SerializationException if deserialisation fails
     */
    @Override
    @Nullable
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return jsonMapper.readValue(data, destinationClass);
        } catch (Exception e) {
            throw new SerializationException(String.format("Failed to deserialize message from topic '%s'", topic), e);
        }
    }
}
