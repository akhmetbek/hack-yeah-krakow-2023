package com.demo.hackyeah.controller;

import com.demo.hackyeah.dtos.Requests.FinanczeskaRequestDto;
import com.demo.hackyeah.dtos.Responses.ChatGptResponseDto;
import com.demo.hackyeah.service.Gpt4Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private final Gpt4Service gtpService;

    @GetMapping
    public ResponseEntity<ChatGptResponseDto> HelloWorld(){
        return ResponseEntity.ok(gtpService.generateText("Hello, can we talk about how to keep context ?"));
    }

    @PostMapping
    public ResponseEntity<ChatGptResponseDto> postPromt(@RequestBody FinanczeskaRequestDto dto){
        return ResponseEntity.ok(gtpService.generateText(dto));
    }
}
