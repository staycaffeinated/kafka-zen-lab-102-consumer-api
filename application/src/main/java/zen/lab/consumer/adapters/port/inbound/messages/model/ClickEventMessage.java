package zen.lab.consumer.adapters.port.inbound.messages.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Abstract base class for all Kafka click-event messages consumed from the
 * {@code kafka-zen.lab-101.traffic-simulator.click-event.received} topic.
 *
 * <p>This class is the inbound DTO hierarchy root. Jackson resolves the concrete
 * subtype at deserialisation time using the {@code eventType} JSON field, which is
 * declared in {@code @JsonTypeInfo} and mapped to subtypes via {@code @JsonSubTypes}.
 *
 * <p>This class is sealed; the only permitted subtypes are:
 * <ul>
 *   <li>{@link ProductViewedEventMessage} — JSON {@code eventType = "product.viewed"}</li>
 *   <li>{@link ProductAddedToCartEventMessage} — JSON {@code eventType = "cart.item.added"}</li>
 * </ul>
 *
 * <p>The {@code userId}, {@code sessionId}, and {@code correlationId} fields are
 * excluded from {@code toString()} to avoid inadvertent PII exposure in logs.
 *
 * <p>Instances are mapped to domain events by {@code ClickEventMessageMapper}.
 */
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"userId", "sessionId", "correlationId"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventType", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProductViewedEventMessage.class, name = "product.viewed"),
    @JsonSubTypes.Type(value = ProductAddedToCartEventMessage.class, name = "cart.item.added")
})
public abstract sealed class ClickEventMessage permits ProductViewedEventMessage, ProductAddedToCartEventMessage {

    private String eventId;
    private String eventType;
    private Instant timestamp;
    private String userId;
    private String sessionId;
    private String correlationId;
    private String partitionKey;
}
