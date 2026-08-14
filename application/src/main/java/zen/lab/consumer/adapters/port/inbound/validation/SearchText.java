/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.port.inbound.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Constraint that verifies a search-text parameter contains only safe characters and does not
 * exceed the maximum permitted length.
 *
 * <p>An empty or {@code null} value passes validation unconditionally, making the annotation
 * safe to use on optional query parameters. Non-empty values must not exceed
 * {@code SearchTextValidator.MAXLENGTH} (currently 24) characters.
 *
 * <p>Consult the OWASP Validation Regex Repository and {@code SearchTextValidator} when
 * tightening the character-set rules for a specific use case.
 *
 * @see SearchTextValidator
 */
@Target({METHOD, FIELD, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = SearchTextValidator.class)
@Documented
public @interface SearchText {
    String message() default "{SearchText.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
