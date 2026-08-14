/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Typed binding for {@code spring.kafka.listener.backoff.*} properties.
 *
 * @param initialInterval initial retry delay in milliseconds
 * @param multiplier      backoff multiplier applied after each retry
 * @param maxInterval     maximum retry delay cap in milliseconds
 * @param maxElapsedTime  total elapsed time before the recoverer is invoked in milliseconds
 */
@ConfigurationProperties(prefix = "spring.kafka.listener.backoff")
public record KafkaBackoffProperties(
        long initialInterval, double multiplier, long maxInterval, long maxElapsedTime) {}
