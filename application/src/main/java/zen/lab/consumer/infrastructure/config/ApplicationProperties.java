/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record ApplicationProperties(
        int defaultPageLimit,
        CorsProperties cors
) {
    public record CorsProperties(String allowedOrigins) {}
}
