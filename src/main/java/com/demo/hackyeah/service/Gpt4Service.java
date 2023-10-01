package com.demo.hackyeah.service;
import com.demo.hackyeah.dtos.Requests.ChatGptRequestDto;
import com.demo.hackyeah.dtos.ChatMessage;
import com.demo.hackyeah.dtos.Requests.FinanczeskaRequestDto;
import com.demo.hackyeah.dtos.Responses.ChatGptResponseDto;
import com.demo.hackyeah.model.Message;
import com.demo.hackyeah.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class Gpt4Service {
    private final String apiKey;
    private final MessageRepository messageRepository;

    @Autowired
    public Gpt4Service(@Value("${gpt.apiKey}") String apiKey,
                       MessageRepository mR) throws IOException {
        this.apiKey = apiKey;
        messageRepository = mR;
    }

    public ChatGptResponseDto generateText(String inputText) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.openai.com/v1/chat/completions"; // Replace with the actual API endpoint

        // Create a request object and send it to the API
        HttpEntity<ChatGptRequestDto> requestEntity = new HttpEntity<>(ChatGptRequestDto.builder().model("gpt-3.5-turbo")
                .messages(List.of(ChatMessage.builder().role("user").content(inputText).build())).temperature(0.3F).build(),
                prepareHeaders());

        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ChatGptResponseDto.class);

        // Process the response and return the generated text
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            messageRepository.save(Message.builder().sessionId(responseEntity.getBody().getId()).role("user").content(inputText).createdDate(Timestamp.from(Instant.now())).build());
            messageRepository.save(Message.builder().sessionId(responseEntity.getBody().getId()).role("assistant").content(responseEntity.getBody().getChoices().get(0).getMessage().getContent()).createdDate(Timestamp.from(Instant.now())).build());
            return responseEntity.getBody();
        } else {
            // Handle error cases
            throw new RuntimeException("Error from GPT-3.5 API: " + responseEntity.getStatusCode());
        }
    }


    public ChatGptResponseDto generateText(FinanczeskaRequestDto dto) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.openai.com/v1/chat/completions"; // Replace with the actual API endpoint

        preparedMessages.add(ChatMessage.builder().role("user").content(dto.getPrompt()).build());
        // Create a request object and send it to the API
        HttpEntity<ChatGptRequestDto> requestEntity = new HttpEntity<>(ChatGptRequestDto.builder().model("gpt-3.5-turbo")
                .messages(preparedMessages).temperature(0.3F).build(),
                prepareHeaders());

        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, ChatGptResponseDto.class);

        // Process the response and return the generated text
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            for (ChatMessage chatMessage : preparedMessages.subList(5, preparedMessages.size())){
                messageRepository.save(Message.builder().sessionId(responseEntity.getBody().getId()).role("user").content(chatMessage.getContent()).createdDate(Timestamp.from(Instant.now())).build());
            }

            messageRepository.save(Message.builder().sessionId(responseEntity.getBody().getId()).role("assistant").content(responseEntity.getBody().getChoices().get(0).getMessage().getContent()).createdDate(Timestamp.from(Instant.now())).build());
            return responseEntity.getBody();
        } else {
            // Handle error cases
            throw new RuntimeException("Error from GPT-3.5 API: " + responseEntity.getStatusCode());
        }
    }
    public List<ChatMessage> preparedMessages = List.of(new ChatMessage("user", "Hello, I am going to send a few csv files to ask questions about them"), new ChatMessage("user", Files.readString(Paths.get("selling_price_for_sqm_housing.csv"))), new ChatMessage("user", Files.readString(Paths.get("selling_price_for_sqm_housing.csv"))));


    private HttpHeaders prepareHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
