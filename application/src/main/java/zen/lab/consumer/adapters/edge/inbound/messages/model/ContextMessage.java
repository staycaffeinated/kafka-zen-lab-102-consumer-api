package zen.lab.consumer.adapters.port.inbound.messages.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Inbound Kafka message DTO that carries the page context embedded in a
 * {@link ProductViewedEventMessage}.
 *
 * <p>Fields mirror those of the domain {@link zen.lab.consumer.domain.model.Context} record and
 * are mapped 1-to-1 by {@code ClickEventMessageMapper.toContext()}.
 *
 * <p>Jackson populates instances via field visibility ({@code @JsonAutoDetect}) so no
 * explicit {@code @JsonProperty} annotations are required.
 */
@Getter
@Builder
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ContextMessage {

    private String pageId;
    private Long position;
    private String source;
    private String referrer;
    private String searchQuery;

    public ContextMessage(String pageId, Long position, String source, String referrer, String searchQuery) {
        this.pageId = pageId;
        this.position = position;
        this.source = source;
        this.referrer = referrer;
        this.searchQuery = searchQuery;
    }
}
