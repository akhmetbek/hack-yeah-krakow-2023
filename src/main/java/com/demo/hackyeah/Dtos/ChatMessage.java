package com.demo.hackyeah.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {
    private String role;
    private String content;
}
