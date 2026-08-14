/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.port.inbound.messages.serdes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import tools.jackson.databind.json.JsonMapper;

class SerdesFactoryTest {

    @Test
    void shouldConstructSuccessfullyWithValidJsonMapper() {
        JsonMapper jsonMapper = new JsonMapper();

        SerdesFactory factory = new SerdesFactory(jsonMapper);

        assertThat(factory).isNotNull();
    }

    @Nested
    class EdgeCases {

        @ParameterizedTest
        @NullSource
        void whenJsonMapperIsNull_thenThrowsNullPointerException(JsonMapper jsonMapper) {
            assertThatThrownBy(() -> new SerdesFactory(jsonMapper)).isInstanceOf(NullPointerException.class);
        }
    }
}
