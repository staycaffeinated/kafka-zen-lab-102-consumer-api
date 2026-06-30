package zen.lab.consumer.adapters.edge.inbound.messages.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
