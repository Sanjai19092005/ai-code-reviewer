package com.aicodereviewer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request model for OpenAI API
 * Contains the message to send to OpenAI
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIRequest {
    
    /**
     * The OpenAI model to use (e.g., gpt-4)
     */
    private String model;
    
    /**
     * List of messages for the conversation
     */
    private List<Message> messages;
    
    /**
     * Maximum tokens for the response
     */
    private int maxTokens;
    
    /**
     * Temperature for response randomness (0.0 to 2.0)
     */
    private double temperature;
    
    /**
     * Inner class for message structure
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}
