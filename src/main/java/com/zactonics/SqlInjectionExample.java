/*
 * INTENTIONALLY VULNERABLE — DEMONSTRATION ONLY.
 *
 * This class exists to show what FindSecBugs reports in real applications.
 * It will fail the build. To make the build pass:
 *   - delete this package, OR
 *   - add <excludes> in the spotbugs / pmd / checkstyle plugin configs.
 *
 * DO NOT COPY THIS CODE INTO PRODUCTION.
 */
package com.zactonics.demo.badexamples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates SQL injection (CWE-89).
 *
 * <p>FindSecBugs detector: SQL_INJECTION_JDBC.
 */
public class SqlInjectionExample {

    /** Looks up users by name — and lets the caller rewrite the query. */
    public List<String> findUsersByName(Connection conn, String userName) throws SQLException {
        // BAD: string-concatenated SQL. Try userName = "x' OR '1'='1".
        String sql = "SELECT id, name FROM users WHERE name = '" + userName + "'";

        List<String> results = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                results.add(rs.getString("name"));
            }
        }
        return results;
    }

    /** Same issue with a different sink — UPDATE this time. */
    public int deactivateUser(Connection conn, String userId) throws SQLException {
        // BAD: caller controls the WHERE clause.
        String sql = "UPDATE users SET active = false WHERE id = '" + userId + "'";
        try (Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }
}
