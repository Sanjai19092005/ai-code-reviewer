package com.aicodereviewer.controller;

import com.aicodereviewer.model.CodeReviewRequest;
import com.aicodereviewer.model.CodeReviewResponse;
import com.aicodereviewer.service.CodeReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for code review endpoints
 * Handles HTTP requests for code review functionality
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class CodeReviewController {

    @Autowired
    private CodeReviewService codeReviewService;

    /**
     * Endpoint to review code
     * Accepts POST requests with code and language
     * Returns AI analysis of the code
     * 
     * @param request The code review request
     * @return CodeReviewResponse with AI analysis
     */
    @PostMapping("/review")
    public ResponseEntity<CodeReviewResponse> reviewCode(@RequestBody CodeReviewRequest request) {
        try {
            // Validate request
            if (request.getCode() == null || request.getCode().trim().isEmpty()) {
                CodeReviewResponse errorResponse = new CodeReviewResponse();
                errorResponse.setSummary("Error: Code cannot be empty");
                errorResponse.setErrors("Please provide code to review");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            if (request.getLanguage() == null || request.getLanguage().trim().isEmpty()) {
                CodeReviewResponse errorResponse = new CodeReviewResponse();
                errorResponse.setSummary("Error: Language cannot be empty");
                errorResponse.setErrors("Please select a programming language");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            // Call service to review code
            CodeReviewResponse response = codeReviewService.reviewCode(request);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Handle unexpected errors
            CodeReviewResponse errorResponse = new CodeReviewResponse();
            errorResponse.setSummary("Error: " + e.getMessage());
            errorResponse.setErrors("An unexpected error occurred while reviewing the code");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    /**
     * Health check endpoint
     * 
     * @return Simple status message
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("AI Code Reviewer API is running");
    }
}
