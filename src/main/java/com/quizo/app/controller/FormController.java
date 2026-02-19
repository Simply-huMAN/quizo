package com.quizo.app.controller;

import com.quizo.app.dto.FormDTO;
import com.quizo.app.dto.FormResponseDTO;
import com.quizo.app.dto.SubmissionDTO;
import com.quizo.app.response.Response;
import com.quizo.app.service.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class FormController {
    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/form")
    public ResponseEntity<String> createForm(@RequestBody FormDTO formDTO) {
        UUID formID = formService.createForm(formDTO);
        return ResponseEntity.ok("Form created successfully, Form ID: " + formID);
    }

    @GetMapping("/form")
    public ResponseEntity<FormResponseDTO> getFormById(@RequestParam UUID id,
                                                           @RequestParam(required = false, defaultValue = "false")
                                               boolean includeQuestions) {
        return ResponseEntity.ok(formService.getFormById(id, includeQuestions));
    }

    @PutMapping("/form")
    public ResponseEntity<String> updateForm(@RequestBody FormDTO formDTO) {
        return ResponseEntity.ok("Form updated successfully");
    }

    @PostMapping("/form/submit")
    public ResponseEntity<Response> submitForm(@RequestBody SubmissionDTO submissionDTO) {
        return ResponseEntity.ok(formService.submitForm(submissionDTO));
    }

    @GetMapping("/form/result")
    public ResponseEntity<Response> getFormResults(@RequestParam UUID submissionId) {
        return ResponseEntity.ok(formService.getFormResults(submissionId));
    }

}
