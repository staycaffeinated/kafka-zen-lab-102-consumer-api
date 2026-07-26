/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import zen.lab.consumer.application.exceptions.ResourceNotFoundException;

class ResourceNotFoundExceptionTest {

    @Test
    void shouldHaveNullMessageWithDefaultConstructor() {
        ResourceNotFoundException ex = new ResourceNotFoundException();

        assertThat(ex.getMessage()).isNull();
    }

    @Test
    void shouldSetDefaultMessageWithThrowableConstructor() {
        Throwable cause = new RuntimeException("underlying cause");

        ResourceNotFoundException ex = new ResourceNotFoundException(cause);

        assertThat(ex.getMessage()).isEqualTo("The requested resource was not found");
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Test
    void shouldSetMessageWithReasonConstructor() {
        String reason = "the requested widget was not found";

        ResourceNotFoundException ex = new ResourceNotFoundException(reason);

        assertThat(ex.getMessage()).isEqualTo(reason);
    }

    @Test
    void shouldSetMessageAndCauseWithReasonAndCauseConstructor() {
        String reason = "no record exists for the given id";
        Throwable cause = new IllegalArgumentException("unknown id");

        ResourceNotFoundException ex = new ResourceNotFoundException(reason, cause);

        assertThat(ex.getMessage()).isEqualTo(reason);
        assertThat(ex.getCause()).isSameAs(cause);
    }

    @Nested
    class EdgeCases {

        @ParameterizedTest
        @NullSource
        void whenThrowableIsNull_defaultConstructorMessageIsSet(Throwable cause) {
            ResourceNotFoundException ex = new ResourceNotFoundException(cause);

            assertThat(ex.getMessage()).isEqualTo("The requested resource was not found");
        }
    }
}
