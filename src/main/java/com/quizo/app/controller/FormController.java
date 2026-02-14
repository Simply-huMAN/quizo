package com.quizo.app.controller;

import com.quizo.app.dto.FormDTO;
import com.quizo.app.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {
    private FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/create-form")
    public ResponseEntity<String> createForm(@RequestBody FormDTO formDTO) {
        formService.createForm(formDTO);
        return ResponseEntity.ok("Form created successfully");
    }

}
