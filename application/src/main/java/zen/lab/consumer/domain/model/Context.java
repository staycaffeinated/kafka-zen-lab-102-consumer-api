package zen.lab.consumer.domain.model;

/**
 * Immutable value object that captures the page context at the moment a click-stream
 * event was produced.
 *
 * <p>Context describes <em>where</em> the user was when the event occurred: which page
 * they were on, the product's position in a listing, the traffic source, the referring
 * URL, and any active search query.
 *
 * <p>Use {@link #builder()} to construct instances:
 * <pre>{@code
 * Context context = Context.builder()
 *     .pageId("pdp-electronics")
 *     .position(3L)
 *     .source("search")
 *     .referrer("https://example.com/search?q=headphones")
 *     .searchQuery("headphones")
 *     .build();
 * }</pre>
 *
 * @param pageId      identifier of the page on which the event occurred
 * @param position    position of the product within a list or grid (1-based)
 * @param source      traffic source (e.g. {@code "search"}, {@code "recommendation"})
 * @param referrer    HTTP referrer URL at the time of the event
 * @param searchQuery search term that led to this page; may be {@code null}
 */
public record Context(String pageId, Long position, String source, String referrer, String searchQuery) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String pageId;
        private Long position;
        private String source;
        private String referrer;
        private String searchQuery;

        private Builder() {}

        public Builder pageId(String pageId) {
            this.pageId = pageId;
            return this;
        }

        public Builder position(Long position) {
            this.position = position;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder referrer(String referrer) {
            this.referrer = referrer;
            return this;
        }

        public Builder searchQuery(String searchQuery) {
            this.searchQuery = searchQuery;
            return this;
        }

        public Context build() {
            return new Context(pageId, position, source, referrer, searchQuery);
        }
    }
}
