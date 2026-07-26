/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.port.outbound;

/**
 * Outbound port for generating resource IDs that can be safely exposed to clients.
 * IDs must be hard to guess (cryptographically strong), URL-friendly, and have
 * at least 160-bit entropy.
 */
public interface ResourceIdSupplier {
    String nextResourceId();
}
