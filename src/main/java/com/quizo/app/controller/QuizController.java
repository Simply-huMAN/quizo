package com.quizo.app.controller;

import com.quizo.app.dto.ChatRequestBody;
import com.quizo.app.dto.FormDTO;
import com.quizo.app.service.ChatService;
import com.quizo.app.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class QuizController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private FormService formService;

    @PostMapping("/completion")
    public ResponseEntity<Object> chatCompletion(@RequestParam("content") String content) {
        return ResponseEntity.of(Optional.ofNullable(chatService.chat(content)));
    }

    @PostMapping("/create-quiz")
    public ResponseEntity<Object> createQuiz(@RequestBody ChatRequestBody requestBody) throws IOException {
        return ResponseEntity.of(Optional.ofNullable(chatService.createQuiz(requestBody.getContent())));
    }

    @PostMapping("/create-form")
    public ResponseEntity<Object> createForm(@RequestBody ChatRequestBody requestBody) throws IOException {
        FormDTO formDto = chatService.createForm(requestBody.getContent());
        UUID formId = formService.createForm(formDto);
        return ResponseEntity.of(Optional.ofNullable(formService.getFormById(formId, true)));
    }

    @GetMapping("/models")
    public ResponseEntity<Object> getModels() {
        return ResponseEntity.ok(chatService.getModels());
    }

}
