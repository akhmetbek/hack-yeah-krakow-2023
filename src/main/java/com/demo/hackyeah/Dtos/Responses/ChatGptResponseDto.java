package com.demo.hackyeah.Dtos.Responses;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatGptResponseDto {
    private String id;
    private String object;
    private Timestamp created;
    private String model;

}
