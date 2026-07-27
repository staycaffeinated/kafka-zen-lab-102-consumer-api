package zen.lab.consumer.domain.model;

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
