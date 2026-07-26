package zen.lab.consumer.domain.model;

public record Context(
        String pageId,
        Long position,
        String source,
        String referrer,
        String searchQuery
) {}
