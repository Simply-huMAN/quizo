package com.quizo.app.controller;

import com.quizo.app.dto.ChatRequestBody;
import com.quizo.app.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class QuizController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/completion")
    public ResponseEntity<Object> chatCompletion(@RequestParam("content") String content) {
        return ResponseEntity.of(Optional.ofNullable(chatService.chat(content)));
    }

    @PostMapping("/create-quiz")
    public ResponseEntity<Object> createQuiz(@RequestBody ChatRequestBody requestBody) throws IOException {
        return ResponseEntity.of(Optional.ofNullable(chatService.createQuiz(requestBody.getContent())));
    }

    @GetMapping("/models")
    public ResponseEntity<Object> getModels() {
        return ResponseEntity.ok(chatService.getModels());
    }

}
