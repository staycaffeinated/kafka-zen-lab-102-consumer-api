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

class BadRequestExceptionTest {

    @Test
    void shouldHaveBadRequestStatusWithDefaultConstructor() {
        BadRequestException ex = new BadRequestException();

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldHaveBadRequestStatusWithThrowableConstructor() {
        Throwable cause = new RuntimeException("underlying cause");

        BadRequestException ex = new BadRequestException(cause);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    void shouldHaveBadRequestStatusWithReasonConstructor() {
        String reason = "the request payload is malformed";

        BadRequestException ex = new BadRequestException(reason);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(ex.getReason()).isEqualTo(reason);
    }

    @Test
    void shouldHaveBadRequestStatusWithReasonAndCauseConstructor() {
        String reason = "invalid field value";
        Throwable cause = new IllegalArgumentException("bad value");

        BadRequestException ex = new BadRequestException(reason, cause);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(ex.getReason()).isEqualTo(reason);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Nested
    class EdgeCases {

        @ParameterizedTest
        @NullSource
        void whenThrowableIsNull_defaultConstructorMessageIsSet(Throwable cause) {
            BadRequestException ex = new BadRequestException(cause);

            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(ex.getReason()).isEqualTo("The request cannot be processed due to client error");
        }
    }
}
