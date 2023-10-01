package com.demo.hackyeah.dtos.Responses;

import lombok.Data;

@Data
public class ChatGptResponseUsageDto {
    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
}
