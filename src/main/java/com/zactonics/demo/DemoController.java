package com.zactonics.demo;

import java.time.Instant;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Provides a tiny JSON API for the static HTML page. */
@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/messages")
    public List<MessageResponse> messages() {
        return List.of(
                new MessageResponse("Spring Boot", "Spring Boot 4 WebMVC API is running.", "OK"),
                new MessageResponse("Java", "Project is configured for Java 25.", "OK"),
                new MessageResponse("Build Quality", "Run mvn clean verify to see tests and reports.", "OK")
        );
    }

    @GetMapping("/health")
    public MessageResponse health() {
        return new MessageResponse("Health", "Server time: " + Instant.now(), "UP");
    }
}
