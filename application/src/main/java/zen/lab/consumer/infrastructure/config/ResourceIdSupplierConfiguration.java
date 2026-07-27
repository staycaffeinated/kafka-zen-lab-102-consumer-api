/*
 * Copyright 2026 [CopyrightOwner]
 */

package zen.lab.consumer.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zen.lab.consumer.application.port.outbound.ResourceIdSupplier;
import zen.lab.consumer.infrastructure.providers.ResourceIdGenerator;

/**
 * Registers the {@link ResourceIdSupplier} outbound port implementation.
 *
 * <p>Creates a {@link zen.lab.consumer.infrastructure.providers.ResourceIdGenerator}
 * instance using the default {@code DRBG} {@code SecureRandom} algorithm. The generator
 * produces 27-character alphanumeric strings with 160-bit entropy, suitable for use as
 * client-visible resource identifiers.
 *
 * <p>To swap the ID generation strategy, replace or override this bean with a different
 * {@link ResourceIdSupplier} implementation without modifying application or domain code.
 */
@Configuration
public class ResourceIdSupplierConfiguration {

    /**
     * Returns a {@link ResourceIdSupplier} backed by a DRBG {@code SecureRandom} instance.
     *
     * @return the resource ID supplier bean
     */
    @Bean
    public ResourceIdSupplier resourceIdSupplier() {
        return new ResourceIdGenerator();
    }
}
