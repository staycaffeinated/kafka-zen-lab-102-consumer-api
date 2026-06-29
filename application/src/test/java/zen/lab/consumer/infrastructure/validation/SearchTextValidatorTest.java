/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.validation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

/**
 * Unit tests of SearchTextValidator
 */
@SuppressWarnings("all")
class SearchTextValidatorTest {
    SearchTextValidator validationUnderTest = new SearchTextValidator();

    @Mock
    ConstraintValidatorContext mockContext;

    @Nested
    class PositiveTestCases {
        @ParameterizedTest
        @ValueSource(strings = {"something", "Something", "SOMETHING", "A", "Abc", "abc"})
        @EmptySource
        void shouldAllowAlphabetic(String candidateText) {
            assertThat(validationUnderTest.isValid(candidateText, mockContext)).isTrue();
        }
    }

    @Nested
    class NegativeTestCases {
        @ParameterizedTest
        @ValueSource(
                strings = {"supercalifragilisticexpialidocious" // too long
                })
        void shouldNotAllowInvalidText(String candidateText) {
            assertThat(validationUnderTest.isValid(candidateText, mockContext)).isFalse();
        }

        @Test
        void shouldTreatNullAsValid() {
            assertThat(validationUnderTest.isValid(null, mockContext)).isTrue();
        }
    }
}
