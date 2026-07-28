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

    /**
     * Generates and returns the next resource identifier.
     *
     * <p>The returned value must be:
     * <ul>
     *   <li>Cryptographically strong (at least 160-bit entropy)</li>
     *   <li>URL-safe (alphanumeric characters only)</li>
     *   <li>Exactly {@link zen.lab.consumer.domain.shared.ResourceIdFormat#RESOURCE_ID_LENGTH}
     *       characters long</li>
     * </ul>
     *
     * @return a non-null, non-empty resource identifier string
     */
    String nextResourceId();
}
