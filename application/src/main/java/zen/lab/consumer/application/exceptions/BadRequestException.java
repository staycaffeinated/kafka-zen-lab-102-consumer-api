/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.exceptions;

import java.io.Serial;

/**
 * Indicates the request could not be processed due to a client error (HTTP 400).
 */

public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1968592496239029423L;

    /**
     * Default Constructor
     */
    public BadRequestException() {
        super();
    }

    /**
     * Constructor
     */
    public BadRequestException(Throwable throwable) {
        super("The request cannot be processed due to client error", throwable);
    }

    /**
     * Constructor with a reason to add to the exception
     * message as explanation.
     *
     * @param reason the associated reason (optional)
     */
    public BadRequestException(String reason) {
        super(reason);
    }

    /**
     * Constructor with a reason to add to the exception
     * message as explanation, as well as a nested exception.
     *
     * @param reason the associated reason (optional)
     * @param cause  a nested exception (optional)
     */
    public BadRequestException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
