/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.config;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Spring MVC type converter that parses a {@link String} into a {@link LocalDate}.
 *
 * <p>This converter is registered automatically via component scanning and enables
 * {@code LocalDate} to be used as a {@code @RequestParam} or {@code @PathVariable} type in
 * REST controllers. It tries each supported format in order and returns the first successful
 * parse.
 *
 * <p>Supported formats (in evaluation order):
 * <ol>
 *   <li>{@code dd-MM-yyyy} (e.g., {@code 27-07-2026})</li>
 *   <li>{@code yyyy-MM-dd} (e.g., {@code 2026-07-27})</li>
 * </ol>
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    /*
     * These formats are arbitrary choices; change these to your desired formats.
     */
    private static final List<String> SUPPORTED_FORMATS = Arrays.asList("dd-MM-yyyy", "yyyy-MM-dd");

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS =
            SUPPORTED_FORMATS.stream().map(DateTimeFormatter::ofPattern).toList();

    /**
     * Converts a date string to a {@link LocalDate} by trying each supported format in order.
     *
     * @param source the date string to parse; must not be null
     * @return the parsed {@code LocalDate}
     * @throws java.time.DateTimeException if the source string does not match any supported format
     */
    @Override
    public LocalDate convert(String source) {
        for (DateTimeFormatter dateTimeFormatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDate.parse(source, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                // keep this empty so all parsers run
            }
        }
        throw new DateTimeException(
                String.format("Unable to parse [%s]. Supported formats are: %s", source, SUPPORTED_FORMATS));
    }
}
