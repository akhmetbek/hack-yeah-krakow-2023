package com.demo.hackyeah.Dtos.Responses;

import com.demo.hackyeah.Dtos.ChatMessage;
import lombok.Data;

@Data
public class ChatGptResponseChoicesDto {
    private ChatMessage message;
    private String finishReason;
    private int index;
}
