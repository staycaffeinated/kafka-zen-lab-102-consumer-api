/*
 * Copyright 2026 [CopyrightOwner]
 */
package zen.lab.consumer.infrastructure.providers;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import lombok.NonNull;
import zen.lab.consumer.application.port.out.ResourceIdSupplier;

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

    public static final int ENTROPY_STRING_LENGTH = ResourceIdSupplier.RESOURCE_ID_LENGTH;

    // Numeric IDs use 160-bit BigInteger, which can reach 49 decimal digits.
    public static final int ENTROPY_MAX_NUMERIC_LENGTH = 49;

    public ResourceIdGenerator() {
        this("DRBG");
    }

    public ResourceIdGenerator(@NonNull String algorithm) {
        try {
            random = SecureRandom.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom();
        }
    }

    public String nextString() {
        return random.ints(ENTROPY_STRING_LENGTH, 0, CIPHER_ALPHABET.length())
                .mapToObj(CIPHER_ALPHABET::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    @Override
    public String nextResourceId() {
        return nextString();
    }

    public Long nextLong() {
        return random.nextLong();
    }

    public String nextNumericResourceId() {
        BigInteger bg = new BigInteger(160, 1, random);
        return bg.toString();
    }
}
