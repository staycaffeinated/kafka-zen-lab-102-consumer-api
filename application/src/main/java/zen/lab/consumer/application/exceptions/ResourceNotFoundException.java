/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.application.exceptions;

import java.io.Serial;

/**
 * A requested resource was not found
 */
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1144457886816201247L;

    /**
     * Default Constructor
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Constructor
     */
    public ResourceNotFoundException(Throwable throwable) {
        super("The requested resource was not found", throwable);
    }

    /**
     * Constructor with a reason to add to the exception
     * message as explanation.
     *
     * @param reason the associated reason (optional)
     */
    public ResourceNotFoundException(String reason) {
        super(reason);
    }

    /**
     * Constructor with a reason to add to the exception
     * message as explanation, as well as a nested exception.
     *
     * @param reason the associated reason (optional)
     * @param cause  a nested exception (optional)
     */
    public ResourceNotFoundException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
