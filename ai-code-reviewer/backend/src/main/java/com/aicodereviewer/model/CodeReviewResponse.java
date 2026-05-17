package com.aicodereviewer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response model for code review
 * Contains the AI analysis results
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeReviewResponse {
    
    /**
     * List of errors detected in the code
     */
    private String errors;
    
    /**
     * Suggestions for improving the code
     */
    private String improvements;
    
    /**
     * Time complexity analysis
     */
    private String timeComplexity;
    
    /**
     * Space complexity analysis
     */
    private String spaceComplexity;
    
    /**
     * Optimized version of the code
     */
    private String optimizedCode;
    
    /**
     * Overall summary of the review
     */
    private String summary;
}
