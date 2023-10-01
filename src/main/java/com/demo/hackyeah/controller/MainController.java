package com.demo.hackyeah.controller;

import com.demo.hackyeah.dtos.Responses.ChatGptResponseDto;
import com.demo.hackyeah.service.Gpt4Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private final Gpt4Service gtpService;

    @GetMapping
    public ResponseEntity<ChatGptResponseDto> HelloWorld(){
        return ResponseEntity.ok(gtpService.generateText("how can I provide the session id to you ?"));
    }
}
