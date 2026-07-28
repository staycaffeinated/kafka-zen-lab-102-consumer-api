/*
 * Copyright 2026 [CopyrightOwner]
 */

package zen.lab.consumer.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration that enforces ISO 8601 date and time formatting globally.
 *
 * <p>Registers a {@link DateTimeFormatterRegistrar} with ISO format enabled, so that
 * {@code LocalDate}, {@code LocalDateTime}, {@code ZonedDateTime}, and related types are
 * bound from request parameters using ISO 8601 patterns (e.g., {@code 2026-07-27}).
 * Individual endpoints may override this default by specifying a
 * {@code @DateTimeFormat} annotation.
 */
@Configuration
public class DateTimeFormatConfiguration implements WebMvcConfigurer {

    /**
     * Registers ISO 8601 date/time formatters with the Spring MVC formatter registry.
     *
     * @param registry the formatter registry to configure
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
}
