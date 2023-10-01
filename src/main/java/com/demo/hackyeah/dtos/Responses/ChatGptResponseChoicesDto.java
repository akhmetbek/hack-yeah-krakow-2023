package com.demo.hackyeah.dtos.Responses;

import com.demo.hackyeah.dtos.ChatMessage;
import lombok.Data;

@Data
public class ChatGptResponseChoicesDto {
    private ChatMessage message;
    private String finishReason;
    private int index;
}
