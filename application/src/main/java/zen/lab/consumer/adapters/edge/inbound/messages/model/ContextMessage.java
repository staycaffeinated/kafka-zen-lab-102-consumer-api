package zen.lab.consumer.adapters.edge.inbound.messages.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
