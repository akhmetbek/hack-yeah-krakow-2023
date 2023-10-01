package com.demo.hackyeah.dtos.Requests;

import com.demo.hackyeah.dtos.ChatMessage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatGptRequestDto {
    private String model;
    private List<ChatMessage> messages;
    private float temperature;
}
