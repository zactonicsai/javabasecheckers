/*
 * INTENTIONALLY VULNERABLE — DEMONSTRATION ONLY.
 * See SqlInjectionExample.java header for context.
 * DO NOT COPY THIS CODE INTO PRODUCTION.
 */
package com.zactonics.demo.badexamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demonstrates path traversal (CWE-22).
 *
 * <p>FindSecBugs detector: PATH_TRAVERSAL_IN.
 */
public class PathTraversalExample {

    private static final String UPLOAD_DIR = "/var/uploads/";

    /** Reads a "user file" — but filename is unchecked, so any file works. */
    public byte[] readUserFile(String filename) throws IOException {
        // BAD: filename = "../../etc/passwd" escapes UPLOAD_DIR.
        // BAD: filename = "/etc/shadow" is an absolute path (Paths.get ignores
        //      UPLOAD_DIR entirely when filename is absolute).
        Path file = Paths.get(UPLOAD_DIR, filename);
        return Files.readAllBytes(file);
    }

    /** Deletes a "user file" — same issue, worse consequences. */
    public void deleteUserFile(String filename) throws IOException {
        Path file = Paths.get(UPLOAD_DIR + filename);
        Files.deleteIfExists(file);
    }
}
