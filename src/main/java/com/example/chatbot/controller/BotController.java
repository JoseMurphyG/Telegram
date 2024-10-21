package com.example.chatbot.controller;

import com.example.chatbot.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {

    private final ChatGptService chatGptService;

    @Autowired
    public BotController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/api/chat")
    public String getChatResponse(@RequestParam String message) {
        return chatGptService.getChatGptResponse(message);
    }

    @GetMapping("/")
    public String home() {
        return "Hola! Soy tu chatbot. ¿En qué puedo ayudarte?";
    }
}