package com.demo.hackyeah.Dtos.Responses;

import lombok.Data;

@Data
public class ChatGptResponseUsageDto {
    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
}
