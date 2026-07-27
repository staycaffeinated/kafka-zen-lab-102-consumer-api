package zen.lab.consumer.domain.shared;

/**
 * Shared constants that define the format of a resource identifier.
 *
 * <p>Both the producer ({@code ResourceIdGenerator}) and the validator
 * ({@code ResourceIdValidator}) reference {@link #RESOURCE_ID_LENGTH} so that
 * the two always agree on the expected identifier length without duplicating the
 * magic number.
 *
 * <p>This class is not instantiable.
 */
public final class ResourceIdFormat {
    private ResourceIdFormat() {}

    /**
     * The exact character length of a valid resource identifier.
     * IDs are 27-character alphanumeric strings generated with 160-bit entropy.
     */
    public static final int RESOURCE_ID_LENGTH = 27;
}
