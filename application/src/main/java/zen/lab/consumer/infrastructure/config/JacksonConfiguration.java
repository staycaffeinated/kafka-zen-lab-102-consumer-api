/*
 * Copyright 2026 [CopyrightOwner]
 */

package zen.lab.consumer.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tools.jackson.databind.json.JsonMapper;

/**
 * Spring configuration that supplies a primary {@code JsonMapper} bean.
 *
 * <p>Calls {@code JsonMapper.builder().findAndAddModules()} to register all Jackson
 * modules available on the classpath, including:
 * <ul>
 *   <li>{@code jackson-datatype-jsr310} — Java 8 date/time types ({@code Instant},
 *       {@code LocalDate}, etc.)</li>
 * </ul>
 *
 * <p>The {@code @Primary} annotation ensures this bean is preferred over any
 * auto-configured {@code ObjectMapper} when a {@code JsonMapper} dependency is
 * resolved, including in the Kafka serde classes and {@code GlobalExceptionHandler}.
 */
@Configuration
public class JacksonConfiguration {

    /**
     * Returns a fully configured {@code JsonMapper} with all classpath-discoverable
     * Jackson modules registered.
     *
     * @return a new primary {@code JsonMapper} instance
     */
    @Bean
    @Primary
    public JsonMapper jsonMapper() {
        return JsonMapper.builder().findAndAddModules().build();
    }
}
