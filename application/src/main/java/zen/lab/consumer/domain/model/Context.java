package zen.lab.consumer.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class Context {
    private String pageId;
    private Long position;
    private String source;
    private String referrer;
    private String searchQuery;
}
