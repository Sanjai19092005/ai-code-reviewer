package com.aicodereviewer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request model for code review
 * Contains the code to be reviewed and the programming language
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeReviewRequest {
    
    /**
     * The code snippet to be reviewed
     */
    private String code;
    
    /**
     * The programming language of the code
     * Supported: Java, Python, C++, JavaScript
     */
    private String language;
}
