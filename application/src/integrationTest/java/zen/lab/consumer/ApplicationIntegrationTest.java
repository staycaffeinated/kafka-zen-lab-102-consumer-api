/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static zen.lab.consumer.adapters.shared.profiles.SpringProfiles.INTEGRATION_TEST;
import static zen.lab.consumer.adapters.shared.profiles.SpringProfiles.TEST;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles({TEST, INTEGRATION_TEST})
@TestPropertySource(properties = "spring.kafka.listener.auto-startup=false")
class ApplicationIntegrationTest {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }
}
