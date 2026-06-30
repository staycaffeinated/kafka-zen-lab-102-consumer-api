/*
 * Copyright 2026 [CopyrightOwner]
 */

package zen.lab.consumer.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zen.lab.consumer.adapters.helpers.ResourceIdGenerator;
import zen.lab.consumer.infrastructure.api.providers.ResourceIdSupplier;

/**
 * Configure application dependencies
 */
@Configuration
public class ResourceIdSupplierConfiguration {

    @Bean
    public ResourceIdSupplier resourceIdSupplier() {
        return new ResourceIdGenerator();
    }
}
