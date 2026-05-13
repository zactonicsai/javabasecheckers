/*
 * INTENTIONALLY VULNERABLE — DEMONSTRATION ONLY.
 * See SqlInjectionExample.java header for context.
 * DO NOT COPY THIS CODE INTO PRODUCTION.
 */
package com.zactonics.demo.badexamples;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Demonstrates weak cryptography (CWE-327), hardcoded credentials (CWE-798),
 * and insecure randomness (CWE-338).
 *
 * <p>FindSecBugs detectors: WEAK_MESSAGE_DIGEST_MD5, DES_USAGE, CIPHER_INTEGRITY,
 * HARD_CODE_KEY, HARD_CODE_PASSWORD, PREDICTABLE_RANDOM.
 */
public class WeakCryptoExample {

    // BAD: hard-coded secret key. In source. In git. In every backup.
    private static final byte[] SECRET_KEY = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);

    // BAD: hard-coded password.
    private static final String DB_PASSWORD = "Pa55word!";

    /** "Hashes" a password with MD5 — broken for password storage since the 90s. */
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        // BAD: MD5 is collision-broken and far too fast for password hashing anyway.
        // Use Argon2id or bcrypt via a library.
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /** Encrypts data using DES in ECB mode — multiple weaknesses in one line. */
    public byte[] encrypt(byte[] plaintext) throws Exception {
        // BAD: DES = 56-bit key, brute-forceable.
        // BAD: ECB mode reveals patterns in plaintext (the "Linux penguin" image).
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY, 0, 8, "DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }

    /** Generates a "session token" with predictable randomness. */
    public String generateSessionToken() {
        // BAD: java.util.Random is seeded from the clock and recoverable from
        // a handful of outputs. Predictable session IDs = account takeover.
        Random random = new Random();
        return Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong());
    }

    /** Reveals the constant just to make sure SpotBugs flags it. */
    public String getDbPassword() {
        return DB_PASSWORD;
    }
}
