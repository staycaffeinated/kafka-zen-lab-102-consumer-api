package zen.lab.consumer.domain.events;

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
public abstract sealed class ClickEvent permits ProductViewedEvent, ProductAddedToCartEvent {

    private String eventId;
    private String eventType;
    private Instant timestamp;
    private String userId;
    private String sessionId;
    private String correlationId;
}
