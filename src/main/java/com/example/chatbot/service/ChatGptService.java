package com.example.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final String apiKey;

    @Autowired
    public ChatGptService(WebClient webClient, @Value("${spring.ai.openai.api-key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public String getChatGptResponse(String prompt) {
        String requestBody = """
                {
                    "model": "text-davinci-003",
                    "prompt": "%s",
                    "max_tokens": 150
                }
                """.formatted(prompt);

        try {
            String response = webClient.post()
                    .uri("completions")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return parseResponse(response);

        } catch (Exception e) {
            return "Error: No se pudo conectar con ChatGPT en este momento. " + e.getMessage();
        }
    }

    private String parseResponse(String response) {
        int startIndex = response.indexOf("\"text\": \"") + 8;
        int endIndex = response.indexOf("\"", startIndex);
        if (startIndex > 0 && endIndex > 0) {
            return response.substring(startIndex, endIndex);
        } else {
            return "Error al procesar la respuesta de ChatGPT.";
        }
    }
}