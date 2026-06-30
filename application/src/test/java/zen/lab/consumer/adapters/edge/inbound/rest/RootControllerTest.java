/*
 * Copyright 2026 [CopyrightOwner]
 */

package zen.lab.consumer.adapters.edge.inbound.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class RootControllerTest {

    private RootController controllerUnderTest;

    @BeforeEach
    void setUp() {
        controllerUnderTest = new RootController();
    }

    @Nested
    class TestRootRoute {
        /*
         * Ensure the root controller handles GET requests
         */
        @Test
        void shouldGetRootPage() {
            var response = controllerUnderTest.getHome();
            assertThat(response).isNotNull();
        }
    }
}
