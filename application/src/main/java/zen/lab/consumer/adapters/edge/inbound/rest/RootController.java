/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.adapters.edge.inbound.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Minimal REST controller providing a root endpoint for liveness checks.
 *
 * <p>Returns HTTP 200 with an empty body for any {@code GET /} request, confirming that the
 * Spring Web context is up. Dedicated liveness and readiness probes are available via Spring
 * Boot Actuator at {@code /actuator/health/liveness} and {@code /actuator/health/readiness}.
 */
@RestController
@RequestMapping("/")
public class RootController {

    /**
     * Liveness check endpoint.
     *
     * @return HTTP 200 with no body
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getHome() {
        return ResponseEntity.ok().build();
    }
}
