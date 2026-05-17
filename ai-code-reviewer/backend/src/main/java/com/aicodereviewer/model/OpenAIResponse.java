package com.aicodereviewer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response model from OpenAI API
 * Contains the AI's response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIResponse {
    
    /**
     * List of choices from the AI
     */
    private List<Choice> choices;
    
    /**
     * Usage statistics
     */
    private Usage usage;
    
    /**
     * Inner class for choice
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private Message message;
        private String finishReason;
        private int index;
    }
    
    /**
     * Inner class for message
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
    
    /**
     * Inner class for usage statistics
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
    }
}
