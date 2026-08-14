package zen.lab.consumer.adapters.port.inbound.messages.serdes;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import tools.jackson.databind.json.JsonMapper;

/**
 * Generic Kafka {@link Serializer} that converts objects to JSON bytes using a
 * provided {@link JsonMapper}.
 *
 * <p>A {@code null} value is serialised as {@code null} bytes (the Kafka convention for
 * tombstone records). Any serialisation failure wraps the underlying exception in a
 * {@link org.apache.kafka.common.errors.SerializationException}.
 *
 * <p>This serialiser is primarily used by the DLQ producer in
 * {@code KafkaConsumerConfiguration} and by {@link SerdesFactory} for stream topology
 * definitions.
 *
 * @param <T> the type of object to serialise
 */
@Slf4j
public class JacksonSerializer<T> implements Serializer<T> {

    private final JsonMapper jsonMapper;

    /**
     * Creates a new serialiser backed by the given {@link JsonMapper}.
     *
     * @param jsonMapper the Jackson mapper to use; must not be null
     * @throws NullPointerException if {@code jsonMapper} is null
     */
    public JacksonSerializer(@Nonnull JsonMapper jsonMapper) {
        this.jsonMapper = Objects.requireNonNull(jsonMapper, "The JsonMapper must not be null");
    }

    /**
     * Serialises {@code data} to a JSON byte array.
     *
     * @param topic the Kafka topic name (used in error messages)
     * @param data  the object to serialise; may be null
     * @return the JSON bytes, or {@code null} if {@code data} is null
     * @throws org.apache.kafka.common.errors.SerializationException if serialisation fails
     */
    @Override
    @Nullable
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        try {
            return jsonMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException(String.format("Error serializing data for topic '%s'", topic), e);
        }
    }
}
