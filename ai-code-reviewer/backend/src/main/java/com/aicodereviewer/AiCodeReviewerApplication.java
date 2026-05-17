package com.aicodereviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application class for AI Code Reviewer
 * This is the entry point for the Spring Boot application
 */
@SpringBootApplication
public class AiCodeReviewerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeReviewerApplication.class, args);
        System.out.println("AI Code Reviewer Backend started successfully!");
        System.out.println("Server running on http://localhost:8080");
    }
}
