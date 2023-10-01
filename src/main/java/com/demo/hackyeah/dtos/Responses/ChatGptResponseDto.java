package com.demo.hackyeah.dtos.Responses;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ChatGptResponseDto {
    private String id;
    private String object;
    private Timestamp created;
    private String model;
    private List<ChatGptResponseChoicesDto> choices;
}
