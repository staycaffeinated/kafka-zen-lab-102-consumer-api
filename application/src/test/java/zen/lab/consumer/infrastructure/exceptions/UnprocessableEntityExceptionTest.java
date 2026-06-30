/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.http.HttpStatus;

class UnprocessableEntityExceptionTest {

    @Test
    void shouldHaveUnprocessableContentStatusWithDefaultConstructor() {
        UnprocessableEntityException ex = new UnprocessableEntityException();

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT);
    }

    @Test
    void shouldHaveUnprocessableContentStatusWithThrowableConstructor() {
        Throwable cause = new RuntimeException("underlying cause");

        UnprocessableEntityException ex = new UnprocessableEntityException(cause);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    void shouldHaveUnprocessableContentStatusWithReasonConstructor() {
        String reason = "the entity failed business validation";

        UnprocessableEntityException ex = new UnprocessableEntityException(reason);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT);
        assertThat(ex.getReason()).isEqualTo(reason);
    }

    @Test
    void shouldHaveUnprocessableContentStatusWithReasonAndCauseConstructor() {
        String reason = "duplicate key detected";
        Throwable cause = new IllegalStateException("key conflict");

        UnprocessableEntityException ex = new UnprocessableEntityException(reason, cause);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT);
        assertThat(ex.getReason()).isEqualTo(reason);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Nested
    class EdgeCases {

        @ParameterizedTest
        @NullSource
        void whenThrowableIsNull_defaultConstructorMessageIsSet(Throwable cause) {
            UnprocessableEntityException ex = new UnprocessableEntityException(cause);

            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT);
            assertThat(ex.getReason()).isEqualTo("Unable to process the resource (or entity)");
        }
    }
}
