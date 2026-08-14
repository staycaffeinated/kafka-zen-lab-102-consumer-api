/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.port.inbound.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Constraint that accepts only alphabetic characters (Unicode letters).
 * Spaces, digits, and punctuation are rejected.
 * For a pattern that allows spaces use {@code @Pattern(regexp = "[a-zA-Z ]")} instead.
 */
@Target({METHOD, FIELD, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = AlphabeticValidator.class)
@Documented
public @interface Alphabetic {
    String message() default "{Alphabetic.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
