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

class ResourceNotFoundExceptionTest {

    @Test
    void shouldHaveNotFoundStatusWithDefaultConstructor() {
        ResourceNotFoundException ex = new ResourceNotFoundException();

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldHaveNotFoundStatusWithThrowableConstructor() {
        Throwable cause = new RuntimeException("underlying cause");

        ResourceNotFoundException ex = new ResourceNotFoundException(cause);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    void shouldHaveNotFoundStatusWithReasonConstructor() {
        String reason = "the requested widget was not found";

        ResourceNotFoundException ex = new ResourceNotFoundException(reason);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(ex.getReason()).isEqualTo(reason);
    }

    @Test
    void shouldHaveNotFoundStatusWithReasonAndCauseConstructor() {
        String reason = "no record exists for the given id";
        Throwable cause = new IllegalArgumentException("unknown id");

        ResourceNotFoundException ex = new ResourceNotFoundException(reason, cause);

        assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(ex.getReason()).isEqualTo(reason);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Nested
    class EdgeCases {

        @ParameterizedTest
        @NullSource
        void whenThrowableIsNull_defaultConstructorMessageIsSet(Throwable cause) {
            ResourceNotFoundException ex = new ResourceNotFoundException(cause);

            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(ex.getReason()).isEqualTo("The requested resource was not found");
        }
    }
}
