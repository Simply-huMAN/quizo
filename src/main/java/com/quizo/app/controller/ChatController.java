package com.quizo.app.controller;

import com.quizo.app.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    public ResponseEntity<Object> chatCompletion(String content) {
        return ResponseEntity.of(Optional.ofNullable(chatService.chatCompletion(content)));
    }

    @GetMapping("/models")
    public ResponseEntity<Object> getModels() {
        return ResponseEntity.ok(chatService.getModels());
    }
}
