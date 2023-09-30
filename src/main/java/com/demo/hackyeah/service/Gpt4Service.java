package com.demo.hackyeah.service;
import com.demo.hackyeah.Dtos.Requests.ChatGptRequestDto;
import com.demo.hackyeah.Dtos.ChatMessage;
import com.demo.hackyeah.Dtos.Responses.ChatGptResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Gpt4Service {
    private final String apiKey; // Load your API key from configuration

    public Gpt4Service(@Value("${gpt4.apiKey}") String apiKey) {
        this.apiKey = apiKey;
    }

    public ChatGptResponseDto generateText(String inputText) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.openai.com/v1/chat/completion"; // Replace with the actual API endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer" + apiKey);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // Create a request object and send it to the API
        HttpEntity<ChatGptRequestDto> requestEntity = new HttpEntity<>(ChatGptRequestDto.builder().model("gpt-3.5-turbo").messages(List.of(ChatMessage.builder().role("user").content(inputText).build())).temperature(0.3F).build(), headers);

        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ChatGptResponseDto.class);

        // Process the response and return the generated text
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            // Handle error cases
            throw new RuntimeException("Error from GPT-4 API: " + responseEntity.getStatusCode());
        }
    }
}
