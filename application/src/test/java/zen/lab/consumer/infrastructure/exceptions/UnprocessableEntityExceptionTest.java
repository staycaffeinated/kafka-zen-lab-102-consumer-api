/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import zen.lab.consumer.application.exceptions.UnprocessableEntityException;

class UnprocessableEntityExceptionTest {

    @Test
    void shouldHaveNullMessageWithDefaultConstructor() {
        UnprocessableEntityException ex = new UnprocessableEntityException();

        assertThat(ex.getMessage()).isNull();
    }

    @Test
    void shouldSetDefaultMessageWithThrowableConstructor() {
        Throwable cause = new RuntimeException("underlying cause");

        UnprocessableEntityException ex = new UnprocessableEntityException(cause);

        assertThat(ex.getMessage()).isEqualTo("Unable to process the resource (or entity)");
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    void shouldSetMessageWithReasonConstructor() {
        String reason = "the entity failed business validation";

        UnprocessableEntityException ex = new UnprocessableEntityException(reason);

        assertThat(ex.getMessage()).isEqualTo(reason);
    }

    @Test
    void shouldSetMessageAndCauseWithReasonAndCauseConstructor() {
        String reason = "duplicate key detected";
        Throwable cause = new IllegalStateException("key conflict");

        UnprocessableEntityException ex = new UnprocessableEntityException(reason, cause);

        assertThat(ex.getMessage()).isEqualTo(reason);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Nested
    class EdgeCases {

        @ParameterizedTest
        @NullSource
        void whenThrowableIsNull_defaultConstructorMessageIsSet(Throwable cause) {
            UnprocessableEntityException ex = new UnprocessableEntityException(cause);

            assertThat(ex.getMessage()).isEqualTo("Unable to process the resource (or entity)");
        }
    }
}
