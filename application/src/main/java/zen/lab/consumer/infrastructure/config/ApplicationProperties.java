/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Typed binding for custom {@code application.*} properties defined in
 * {@code application.yml}.
 *
 * <p>Spring Boot binds these properties automatically when this record is present
 * on the classpath and the {@code @ConfigurationProperties} annotation is processed.
 *
 * <p>Current properties:
 * <ul>
 *   <li>{@code application.default-page-limit} — default page size for pageable endpoints
 *       (default: {@code 25})</li>
 *   <li>{@code application.cors.allowed-origins} — CORS origin pattern
 *       (default: {@code http://*.localhost})</li>
 * </ul>
 *
 * @param defaultPageLimit default page size for pageable REST endpoints
 * @param cors             CORS configuration sub-properties
 */
@ConfigurationProperties(prefix = "application")
public record ApplicationProperties(int defaultPageLimit, CorsProperties cors) {

    /**
     * CORS sub-properties nested under {@code application.cors.*}.
     *
     * @param allowedOrigins origin pattern passed to Spring MVC's CORS registry
     */
    public record CorsProperties(String allowedOrigins) {}
}
