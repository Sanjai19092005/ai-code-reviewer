package com.aicodereviewer.service;

import com.aicodereviewer.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class for code review functionality
 * Handles communication with OpenAI API
 */
@Service
public class CodeReviewService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    @Value("${openai.api.model}")
    private String openaiApiModel;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reviews the given code using OpenAI API
     * 
     * @param request The code review request containing code and language
     * @return CodeReviewResponse with AI analysis
     */
    public CodeReviewResponse reviewCode(CodeReviewRequest request) {
        try {
            // Build the prompt for OpenAI
            String prompt = buildPrompt(request.getCode(), request.getLanguage());

            // Create OpenAI request
            OpenAIRequest openAIRequest = new OpenAIRequest();
            openAIRequest.setModel(openaiApiModel);
            openAIRequest.setMessages(java.util.List.of(
                new OpenAIRequest.Message("user", prompt)
            ));
            openAIRequest.setMaxTokens(2000);
            openAIRequest.setTemperature(0.7);

            // Set up HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openaiApiKey);

            // Create HTTP entity
            HttpEntity<OpenAIRequest> entity = new HttpEntity<>(openAIRequest, headers);

            // Make API call
            ResponseEntity<String> response = restTemplate.exchange(
                openaiApiUrl,
                HttpMethod.POST,
                entity,
                String.class
            );

            // Parse response
            return parseOpenAIResponse(response.getBody());

        } catch (Exception e) {
            // Return error response if API call fails
            CodeReviewResponse errorResponse = new CodeReviewResponse();
            errorResponse.setSummary("Error: " + e.getMessage());
            errorResponse.setErrors("Unable to connect to OpenAI API. Please check your API key.");
            errorResponse.setImprovements("");
            errorResponse.setTimeComplexity("");
            errorResponse.setSpaceComplexity("");
            errorResponse.setOptimizedCode("");
            return errorResponse;
        }
    }

    /**
     * Builds the prompt for OpenAI API
     * 
     * @param code The code to review
     * @param language The programming language
     * @return Formatted prompt string
     */
    private String buildPrompt(String code, String language) {
        return String.format(
            "You are an expert code reviewer. Please review the following %s code and provide a detailed analysis.\n\n" +
            "CODE:\n%s\n\n" +
            "Please provide your response in the following JSON format:\n" +
            "{\n" +
            "  \"errors\": \"List any errors or bugs found\",\n" +
            "  \"improvements\": \"Suggest improvements for code quality, readability, and best practices\",\n" +
            "  \"timeComplexity\": \"Analyze and explain the time complexity\",\n" +
            "  \"spaceComplexity\": \"Analyze and explain the space complexity\",\n" +
            "  \"optimizedCode\": \"Provide an optimized version of the code\",\n" +
            "  \"summary\": \"Brief summary of the overall code quality\"\n" +
            "}\n\n" +
            "Make sure your response is valid JSON.",
            language,
            code
        );
    }

    /**
     * Parses the OpenAI API response
     * 
     * @param responseBody The raw response body from OpenAI
     * @return CodeReviewResponse with parsed data
     */
    private CodeReviewResponse parseOpenAIResponse(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            String content = root.path("choices").get(0).path("message").path("content").asText();

            // Try to parse the JSON content from OpenAI
            JsonNode contentJson = objectMapper.readTree(content);

            CodeReviewResponse response = new CodeReviewResponse();
            response.setErrors(contentJson.path("errors").asText(""));
            response.setImprovements(contentJson.path("improvements").asText(""));
            response.setTimeComplexity(contentJson.path("timeComplexity").asText(""));
            response.setSpaceComplexity(contentJson.path("spaceComplexity").asText(""));
            response.setOptimizedCode(contentJson.path("optimizedCode").asText(""));
            response.setSummary(contentJson.path("summary").asText(""));

            return response;

        } catch (Exception e) {
            // If parsing fails, return the raw content
            CodeReviewResponse response = new CodeReviewResponse();
            response.setSummary("Review completed (JSON parsing failed, showing raw response)");
            response.setErrors("");
            response.setImprovements(responseBody);
            response.setTimeComplexity("");
            response.setSpaceComplexity("");
            response.setOptimizedCode("");
            return response;
        }
    }
}
