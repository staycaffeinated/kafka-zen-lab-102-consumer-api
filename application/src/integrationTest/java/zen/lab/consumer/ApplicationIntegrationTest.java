/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import zen.lab.consumer.common.persistence.RegisterDatabaseProperties;

@SpringBootTest
class ApplicationIntegrationTest implements RegisterDatabaseProperties {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }
}
