/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration applying CORS rules from {@link ApplicationProperties}.
 *
 * <p>Applies to all paths ({@code /**}). The allowed-origin pattern is read from
 * {@code application.cors.allowed-origins} (default: {@code http://*.localhost}).
 * Credentials are permitted, and all standard HTTP methods and headers are allowed.
 *
 * <p>Override {@code application.cors.allowed-origins} in an environment-specific
 * profile (e.g., {@code application-prod.yml}) to restrict origins in production.
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final ApplicationProperties applicationProperties;

    /**
     * Registers a CORS mapping that applies to all paths and uses the configured
     * origin pattern.
     *
     * @param registry the Spring MVC CORS registry to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOriginPatterns(applicationProperties.cors().allowedOrigins());
    }
}
