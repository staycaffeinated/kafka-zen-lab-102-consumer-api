package zen.lab.consumer.adapters.port.inbound.messages.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

class ContextMessageTest {

    private static final String PAGE_ID = "page-abc";
    private static final Long POSITION = 3L;
    private static final String SOURCE = "search";
    private static final String REFERRER = "https://example.com";
    private static final String SEARCH_QUERY = "blue sneakers";

    @Nested
    class Construction {

        @Test
        void builderSetsAllFields() {
            ContextMessage msg = ContextMessage.builder()
                    .pageId(PAGE_ID)
                    .position(POSITION)
                    .source(SOURCE)
                    .referrer(REFERRER)
                    .searchQuery(SEARCH_QUERY)
                    .build();

            assertThat(msg.getPageId()).isEqualTo(PAGE_ID);
            assertThat(msg.getPosition()).isEqualTo(POSITION);
            assertThat(msg.getSource()).isEqualTo(SOURCE);
            assertThat(msg.getReferrer()).isEqualTo(REFERRER);
            assertThat(msg.getSearchQuery()).isEqualTo(SEARCH_QUERY);
        }

        @Test
        void allArgsConstructorSetsAllFields() {
            ContextMessage msg = new ContextMessage(PAGE_ID, POSITION, SOURCE, REFERRER, SEARCH_QUERY);

            assertThat(msg.getPageId()).isEqualTo(PAGE_ID);
            assertThat(msg.getPosition()).isEqualTo(POSITION);
            assertThat(msg.getSource()).isEqualTo(SOURCE);
            assertThat(msg.getReferrer()).isEqualTo(REFERRER);
            assertThat(msg.getSearchQuery()).isEqualTo(SEARCH_QUERY);
        }

        @Test
        void noArgsConstructorProducesNullFields() {
            ContextMessage msg = new ContextMessage();

            assertThat(msg.getPageId()).isNull();
            assertThat(msg.getPosition()).isNull();
            assertThat(msg.getSource()).isNull();
            assertThat(msg.getReferrer()).isNull();
            assertThat(msg.getSearchQuery()).isNull();
        }

        @Test
        void builderWithNullFieldsAllowed() {
            ContextMessage msg = ContextMessage.builder().build();

            assertThat(msg.getPageId()).isNull();
            assertThat(msg.getPosition()).isNull();
        }
    }

    @Nested
    class JsonDeserialization {

        private final JsonMapper jsonMapper = new JsonMapper();

        @Test
        void deserializesAllFieldsFromJson() throws Exception {
            String json =
                    """
                    {
                      "pageId": "%s",
                      "position": %d,
                      "source": "%s",
                      "referrer": "%s",
                      "searchQuery": "%s"
                    }
                    """
                            .formatted(PAGE_ID, POSITION, SOURCE, REFERRER, SEARCH_QUERY);

            ContextMessage msg = jsonMapper.readValue(json, ContextMessage.class);

            assertThat(msg.getPageId()).isEqualTo(PAGE_ID);
            assertThat(msg.getPosition()).isEqualTo(POSITION);
            assertThat(msg.getSource()).isEqualTo(SOURCE);
            assertThat(msg.getReferrer()).isEqualTo(REFERRER);
            assertThat(msg.getSearchQuery()).isEqualTo(SEARCH_QUERY);
        }

        @Test
        void deserializesPartialJsonWithNullsForMissingFields() throws Exception {
            String json =
                    """
                    {
                      "pageId": "%s"
                    }
                    """
                            .formatted(PAGE_ID);

            ContextMessage msg = jsonMapper.readValue(json, ContextMessage.class);

            assertThat(msg.getPageId()).isEqualTo(PAGE_ID);
            assertThat(msg.getPosition()).isNull();
            assertThat(msg.getSource()).isNull();
            assertThat(msg.getReferrer()).isNull();
            assertThat(msg.getSearchQuery()).isNull();
        }

        @Test
        void deserializesEmptyJsonObjectToAllNullFields() throws Exception {
            ContextMessage msg = jsonMapper.readValue("{}", ContextMessage.class);

            assertThat(msg.getPageId()).isNull();
            assertThat(msg.getPosition()).isNull();
            assertThat(msg.getSource()).isNull();
            assertThat(msg.getReferrer()).isNull();
            assertThat(msg.getSearchQuery()).isNull();
        }
    }
}
