package zen.lab.consumer.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ContextTest {

    @Test
    void builderMustConstructContextWithAllFields() {
        var context = Context.builder()
                .pageId("home-page")
                .position(3L)
                .source("web")
                .referrer("https://example.com")
                .searchQuery("running shoes")
                .build();

        assertThat(context.pageId()).isEqualTo("home-page");
        assertThat(context.position()).isEqualTo(3L);
        assertThat(context.source()).isEqualTo("web");
        assertThat(context.referrer()).isEqualTo("https://example.com");
        assertThat(context.searchQuery()).isEqualTo("running shoes");
    }

    @Test
    void builderMustConstructContextWithNullOptionalFields() {
        var context = Context.builder().pageId("home-page").source("web").build();

        assertThat(context.pageId()).isEqualTo("home-page");
        assertThat(context.source()).isEqualTo("web");
        assertThat(context.position()).isNull();
        assertThat(context.referrer()).isNull();
        assertThat(context.searchQuery()).isNull();
    }
}
