/*
 * INTENTIONALLY BUGGY — DEMONSTRATION ONLY.
 * See SqlInjectionExample.java header for context.
 *
 * Triggers SpotBugs/PMD findings:
 *   - empty catch block
 *   - String comparison with ==
 *   - unused private field
 *   - guaranteed null pointer dereference
 *   - mutable public static array
 *   - returning null from a Collection method
 */
package com.zactonics.demo.badexamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Collection of common Java bug patterns.
 */
public final class BuggyCodeExample {

    /** Unused private field — PMD UnusedPrivateField, SpotBugs URF_UNREAD_FIELD. */
    private final String unused = "never read";

    /** Mutable public static array — SpotBugs MS_MUTABLE_ARRAY. */
    public static final String[] CONSTANTS = {"a", "b", "c"};

    /** Compares strings with ==. SpotBugs ES_COMPARING_STRINGS_WITH_EQ. */
    public boolean isAdmin(String role) {
        return role == "admin";
    }

    /** Empty catch block. PMD EmptyCatchBlock, SpotBugs DE_MIGHT_IGNORE. */
    public String readFileOrEmpty(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            // silently ignored
        }
        return "";
    }

    /** Guaranteed null dereference. SpotBugs NP_ALWAYS_NULL. */
    public int lengthOfNothing() {
        String s = null;
        return s.length();
    }

    /** Returning null from a List method. PMD ReturnEmptyCollectionRatherThanNull. */
    public List<String> findNames(boolean any) {
        if (!any) {
            return null;
        }
        return Collections.singletonList("Ada");
    }

    /** Catches Exception (too broad) and rethrows as RuntimeException. */
    public void doWork() {
        try {
            riskyCall();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void riskyCall() throws Exception {
        throw new Exception("boom");
    }
}
