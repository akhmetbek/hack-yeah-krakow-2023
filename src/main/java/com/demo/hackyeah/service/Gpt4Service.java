package com.demo.hackyeah.service;
import com.demo.hackyeah.dtos.Requests.ChatGptRequestDto;
import com.demo.hackyeah.dtos.ChatMessage;
import com.demo.hackyeah.dtos.Responses.ChatGptResponseDto;
import com.demo.hackyeah.model.Message;
import com.demo.hackyeah.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class Gpt4Service {
    private final String apiKey = "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";
    private final MessageRepository messageRepository;

    public ChatGptResponseDto generateText(String inputText) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.openai.com/v1/chat/completions"; // Replace with the actual API endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // Create a request object and send it to the API
        HttpEntity<ChatGptRequestDto> requestEntity = new HttpEntity<>(ChatGptRequestDto.builder().model("gpt-3.5-turbo").messages(List.of(ChatMessage.builder().role("user").content(inputText).build())).temperature(0.3F).build(), headers);

        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ChatGptResponseDto.class);

        // Process the response and return the generated text
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            messageRepository.save(Message.builder().sessionId(responseEntity.getBody().getId()).role("user").content(inputText).build());
            messageRepository.save(Message.builder().sessionId(responseEntity.getBody().getId()).role("assistant").content(responseEntity.getBody().getChoices().get(0).getMessage().getContent()).build());
            return responseEntity.getBody();
        } else {
            // Handle error cases
            throw new RuntimeException("Error from GPT-4 API: " + responseEntity.getStatusCode());
        }
    }


}
