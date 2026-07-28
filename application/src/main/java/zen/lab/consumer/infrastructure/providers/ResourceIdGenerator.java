/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.providers;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import lombok.NonNull;
import zen.lab.consumer.application.port.outbound.ResourceIdSupplier;
import zen.lab.consumer.domain.shared.ResourceIdFormat;

/**
 * Produces secure random values with 160-bit entropy, suitable for use as client-visible
 * resource IDs. Values are URL-friendly and exceed the OAuth2 recommendation of 128–160 bits.
 *
 * Fun facts:
 * 160 bits = 2^160 ~= 1.46 x 10^48 possible values.
 * The number of liters of water on the Earth is about 1.26 x 10^21.
 *
 * The strength of the secure random generator can be configured in the java.security file.
 * See https://metebalci.com/blog/everything-about-javas-securerandom/
 */
public class ResourceIdGenerator implements ResourceIdSupplier {

    private SecureRandom random;

    // Alphanumeric cipher alphabet — excludes URL-unsafe characters per RFC 1738.
    private static final String CIPHER_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final int ENTROPY_STRING_LENGTH = ResourceIdFormat.RESOURCE_ID_LENGTH;

    // Numeric IDs use 160-bit BigInteger, which can reach 49 decimal digits.
    public static final int ENTROPY_MAX_NUMERIC_LENGTH = 49;

    /**
     * Creates a generator backed by the {@code DRBG} {@link SecureRandom} algorithm.
     * Falls back to the default {@code SecureRandom} if {@code DRBG} is unavailable on
     * the current JVM.
     */
    public ResourceIdGenerator() {
        this("DRBG");
    }

    /**
     * Creates a generator backed by the named {@link SecureRandom} algorithm.
     *
     * <p>If the algorithm is not available on the current JVM, the constructor falls back
     * to the platform default {@code SecureRandom}.
     *
     * @param algorithm the {@code SecureRandom} algorithm name (e.g., {@code "DRBG"},
     *                  {@code "SHA1PRNG"}); must not be null
     */
    public ResourceIdGenerator(@NonNull String algorithm) {
        try {
            random = SecureRandom.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom();
        }
    }

    /**
     * Generates a random alphanumeric string of exactly {@link #ENTROPY_STRING_LENGTH}
     * characters drawn from the URL-safe cipher alphabet.
     *
     * @return a non-null, 27-character alphanumeric string
     */
    public String nextString() {
        return random.ints(ENTROPY_STRING_LENGTH, 0, CIPHER_ALPHABET.length())
                .mapToObj(CIPHER_ALPHABET::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * {@inheritDoc}
     *
     * <p>Delegates to {@link #nextString()}.
     */
    @Override
    public String nextResourceId() {
        return nextString();
    }

    /**
     * Returns a cryptographically random {@code long} value.
     *
     * @return a uniformly distributed random long
     */
    public Long nextLong() {
        return random.nextLong();
    }

    /**
     * Generates a 160-bit random value returned as a decimal string.
     *
     * <p>The resulting string has at most {@link #ENTROPY_MAX_NUMERIC_LENGTH} digits.
     * Use this method when a purely numeric identifier is required (e.g., for database
     * primary keys that must be numeric).
     *
     * @return a non-null decimal string representation of a 160-bit random number
     */
    public String nextNumericResourceId() {
        BigInteger bg = new BigInteger(160, 1, random);
        return bg.toString();
    }
}
