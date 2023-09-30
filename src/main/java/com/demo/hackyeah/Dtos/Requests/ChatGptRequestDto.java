package com.demo.hackyeah.Dtos.Requests;

import com.demo.hackyeah.Dtos.ChatMessage;
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
