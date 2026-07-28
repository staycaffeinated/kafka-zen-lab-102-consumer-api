/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Spring Boot entry point for the kafka-zen-lab-102-consumer-api application.
 *
 * <p>{@code @ConfigurationPropertiesScan} activates scanning for
 * {@code @ConfigurationProperties}-annotated beans (e.g., {@code ApplicationProperties})
 * without requiring explicit {@code @EnableConfigurationProperties} declarations.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    /**
     * Application entry point.
     *
     * @param args command-line arguments passed through to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
