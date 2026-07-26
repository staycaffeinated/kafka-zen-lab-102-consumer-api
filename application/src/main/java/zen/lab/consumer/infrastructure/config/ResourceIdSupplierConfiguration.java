/*
 * Copyright 2026 [CopyrightOwner]
 */

package zen.lab.consumer.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zen.lab.consumer.application.port.outbound.ResourceIdSupplier;
import zen.lab.consumer.infrastructure.providers.ResourceIdGenerator;

@Configuration
public class ResourceIdSupplierConfiguration {

    @Bean
    public ResourceIdSupplier resourceIdSupplier() {
        return new ResourceIdGenerator();
    }
}
