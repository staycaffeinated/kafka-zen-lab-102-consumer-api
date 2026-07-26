/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import zen.lab.consumer.application.exceptions.BadRequestException;

class BadRequestExceptionTest {

    @Test
    void shouldHaveNullMessageWithDefaultConstructor() {
        BadRequestException ex = new BadRequestException();

        assertThat(ex.getMessage()).isNull();
    }

    @Test
    void shouldSetDefaultMessageWithThrowableConstructor() {
        Throwable cause = new RuntimeException("underlying cause");

        BadRequestException ex = new BadRequestException(cause);

        assertThat(ex.getMessage()).isEqualTo("The request cannot be processed due to client error");
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    void shouldSetMessageWithReasonConstructor() {
        String reason = "the request payload is malformed";

        BadRequestException ex = new BadRequestException(reason);

        assertThat(ex.getMessage()).isEqualTo(reason);
    }

    @Test
    void shouldSetMessageAndCauseWithReasonAndCauseConstructor() {
        String reason = "invalid field value";
        Throwable cause = new IllegalArgumentException("bad value");

        BadRequestException ex = new BadRequestException(reason, cause);

        assertThat(ex.getMessage()).isEqualTo(reason);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Nested
    class EdgeCases {

        @ParameterizedTest
        @NullSource
        void whenThrowableIsNull_defaultConstructorMessageIsSet(Throwable cause) {
            BadRequestException ex = new BadRequestException(cause);

            assertThat(ex.getMessage()).isEqualTo("The request cannot be processed due to client error");
        }
    }
}
